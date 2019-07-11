package com.shanghai.logistics.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.ArcOptions;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.BuildingOverlayOptions;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.adapter.map.GaodeAdapter;
import com.shanghai.logistics.ui.logistics_activity.AddStoresActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GaodeActivity extends SimpleActivity implements AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener, Inputtips.InputtipsListener {
    private static final String TAG = "GaodeActivity";
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_right_content)
    TextView mTvRightTitle;
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.img_back_start)
    ImageView mImgBackStart;
    @BindView(R.id.rv_address)
    RecyclerView mRvAddress;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    //初始化地图控制器对象
    AMap aMap;
    //实现定位蓝点
    MyLocationStyle myLocationStyle;
    //声明mlocationClient对象
    public AMapLocationClient mLocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    private GeocodeSearch geocoderSearch;

    String AddressName;

    Intent intent;
    int type;
    GaodeAdapter gaodeAdapter;
    List<Tip> mTipList;
    InputtipsQuery inputquery;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    AMap.InfoWindowAdapter mInfoWindowAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_gaode;
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("地图坐标");
        mTvRightTitle.setVisibility(View.VISIBLE);
        mTvRightTitle.setText("完成");
        Bundle bundle = getIntent().getBundleExtra(Constants.ACTIVITY_TYPE);
        if (bundle != null) {
            type = bundle.getInt(Constants.ACTIVITY_TYPE);
        }
        mTipList = new ArrayList<>();
        gaode();
        gaodeAdapter = new GaodeAdapter(mTipList);
        mRvAddress.setLayoutManager(new LinearLayoutManager(this));
        mRvAddress.setAdapter(gaodeAdapter);
        gaodeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTipList = adapter.getData();
                LatLng latLng = new LatLng(mTipList.get(position).getPoint().getLatitude(), mTipList.get(position).getPoint().getLongitude());
                mapMoveCenter(latLng);
                mRvAddress.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean b = hasPermission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (!b) {
            requestPermission(Constants.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

    }

    @OnClick({R.id.tv_right_content, R.id.img_back, R.id.img_back_start, R.id.tv_search})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right_content:
                switch (type) {
                    case Constants.ADD_STORES_ACTIVITY:
                        intent = new Intent(this, AddStoresActivity.class);
                        Bundle bundle = new Bundle();
                        Log.i(TAG, "AddressName: " + AddressName);
                        bundle.putString(Constants.ADDRESS_NAME, AddressName);
                        intent.putExtra(Constants.ADDRESS_NAME, bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                        break;
                }
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.img_back_start:
                aMap.setMyLocationEnabled(true);
                break;
            case R.id.tv_search:
                Log.i(TAG, "onClick: ");
                inputquery = new InputtipsQuery(mEtAddress.getText().toString(), "上海");
                inputquery.setCityLimit(true);//限制在当前城市
                Inputtips inputTips = new Inputtips(GaodeActivity.this, inputquery);
                inputTips.setInputtipsListener(this);
                inputTips.requestInputtipsAsyn();

                break;

        }
    }


    void gaode() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
        mUiSettings.setZoomControlsEnabled(false);
        //设置定位回调监听
        //   mLocationClient.setLocationListener(mLocationListener);
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        //  myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_coordinate));
//        myLocationStyle.strokeColor(R.color.red);
//        myLocationStyle.radiusFillColor(R.color.red);
//        myLocationStyle.strokeWidth(5);
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        //    aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//只定位一次。
        //   myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
        //  myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        //  myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        //以下三种模式从5.1.0版本开始提供
        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
        //  myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
        //  myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，地图依照设备方向旋转，并且蓝点会跟随设备移动。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mLocationClient.setLocationListener(this);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(100000);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(true);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

        selectAddress();

        geocoderSearch = new GeocodeSearch(getApplicationContext());
        geocoderSearch.setOnGeocodeSearchListener(this);

        // 绑定 Marker 被点击事件
//        mInfoWindowAdapter = new AMap.ImageInfoWindowAdapter() {
//            @Override
//            public long getInfoWindowUpdateTime() {
//                return 0;
//            }
//
//            @Override
//            public View getInfoWindow(Marker marker) {
//                View infoWindow = null;
//                if (infoWindow == null) {
//                    infoWindow = LayoutInflater.from(GaodeActivity.this).inflate(
//                            R.layout.item_gaode_list, null);
//                }
//                render(marker, infoWindow);
//                return infoWindow;
//            }
//
//            @Override
//            public View getInfoContents(Marker marker) {
//                return null;
//            }
//        };
//        aMap.setOnMarkerClickListener(markerClickListener);
//        aMap.setInfoWindowAdapter(mInfoWindowAdapter);//AMap类中
    }

    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view) {
    //如果想修改自定义Infow中内容，请通过view找到它并修改
    }

    // 定义 Marker 点击事件监听
    AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
        // marker 对象被点击时回调的接口
        // 返回 true 则表示接口已响应事件，否则返回false
        @Override
        public boolean onMarkerClick(Marker marker) {
            return false;
        }
    };

    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        gaodeAdapter.setNewData(list);
//        for (int j = 0; j < list.size(); j++) {
//            Log.i(TAG, "onGetInputtips: " + list.get(j).getAddress() + list.get(j).getName() + list.size());
//        }

    }

    //地图选点
    void selectAddress() {

        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                LatLng latLng = cameraPosition.target;
                mapMoveCenter(latLng);

                LatLonPoint latLonPoint = new LatLonPoint(latLng.latitude, latLng.longitude);
                RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 10,
                        GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
                geocoderSearch.getFromLocationAsyn(query);// 设置异步逆地理编码请求
            }
        });


    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {
                AddressName = result.getRegeocodeAddress().getFormatAddress();
                Log.i(TAG, "onRegeocodeSearched: " + AddressName);
            }
        } else {
            ToastUtils.show("暂未获取到信息");
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {

    }

    /**
     * 将地图移动到中心点
     *
     * @param arg0
     */
    private void mapMoveCenter(LatLng arg0) {
        aMap.clear();
        aMap.setMyLocationEnabled(false);
//        CameraPosition update = new CameraPosition.Builder()
//                .target(arg0)
//                .zoom(18)
//                .build();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(arg0, 18, 30, 0));
        aMap.animateCamera(cameraUpdate);
        aMap.moveCamera(cameraUpdate);
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.ic_coordinate);
        MarkerOptions option = new MarkerOptions().position(arg0).icon(mCurrentMarker);
        // 在地图上添加Marker，并显示
        // option.setFlat(true);//设置marker平贴地图效果
        aMap.addMarker(option);
        Log.i(TAG, "mapMoveCenter: ");
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                Log.i(TAG, "onLocationChanged: " + amapLocation.getAddress());
                AddressName = amapLocation.getAddress();
                LatLng cenpt = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());

//                CameraPosition update = new CameraPosition.Builder()
//                        .target(cenpt)
//                        .zoom(18)
//                        .build();

                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(cenpt, 18, 30, 0));
                aMap.moveCamera(cameraUpdate);

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMapView != null) {
            mMapView.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 申请指定的权限.
     */
    public void requestPermission(int requestCode, String... permissions) {

        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }


    /**
     * 判断是否有指定的权限
     */
    public boolean hasPermission(String... permissions) {

        for (String permisson : permissions) {
            if (ActivityCompat.checkSelfPermission(App.getInstance(), permisson)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        } else {
            ToastUtils.show("您已拒绝使用定位功能");
        }


    }


}
