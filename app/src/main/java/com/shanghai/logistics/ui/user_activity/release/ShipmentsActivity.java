package com.shanghai.logistics.ui.user_activity.release;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.base.connectors.user.UserCarModelConnector;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.user.CarLengthEntity;
import com.shanghai.logistics.models.entity.user.CarModelEntity;
import com.shanghai.logistics.models.http.Uploading;
import com.shanghai.logistics.models.services.ApiService;
import com.shanghai.logistics.models.services.UserService;
import com.shanghai.logistics.presenters.user.UserCarModelPresenter;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerAdapter;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerListAdapter;
import com.shanghai.logistics.ui.user_activity.home_detail.AddressActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.VehicleLengthActivity;
import com.shanghai.logistics.ui.user_fragment.order.TakeOrdersFragment;
import com.shanghai.logistics.ui.user_fragment.release.CarTypeFragment;
import com.shanghai.logistics.util.DataUtil;
import com.shanghai.logistics.util.FileUploadUtil;
import com.yzq.zxinglibrary.common.Constant;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * 发货界面
 */
public class ShipmentsActivity extends BaseActivity<UserCarModelPresenter> implements UserCarModelConnector.View, AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener {
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.tv_che_type)
    TextView mTvCheType;
    @BindView(R.id.tv_pay_type)
    TextView mTvPayType;

    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.et_end_address)
    EditText mEtEndAddress;
    @BindView(R.id.et_goods)
    EditText mEtGoods;

    @BindView(R.id.et_volume)
    EditText mEtVolume;
    @BindView(R.id.et_weight)
    EditText mEtWeight;
    @BindView(R.id.et_pieces)
    EditText mEtPieces;
    @BindView(R.id.et_receipt)
    EditText mEtReceipt;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.et_driver)
    EditText mEtDriver;
    @BindView(R.id.et_estimatedCost)
    EditText mEtEstimatedCost;
    @BindView(R.id.rb_is)
    RadioButton mRbIsPhone;
    @BindView(R.id.rb_no)
    RadioButton mRbNoPhone;
    @BindView(R.id.tv_handling_type)
    TextView mTvLoadMethod;
    int isPhone;
    TimePickerView pvTime;

    @BindView(R.id.map)
    MapView mMapView;
    //初始化地图控制器对象
    AMap aMap;
    //实现定位蓝点
    MyLocationStyle myLocationStyle;
    //声明mlocationClient对象
    public AMapLocationClient mLocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    private GeocodeSearch geocoderSearch;

    private static final String TAG = "ShipmentsActivity";
    @BindView(R.id.st_tab)
    SlidingTabLayout mStTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    ViewPagerAdapter mViewPagerAdapter;
    List<Fragment> fragmentList;
    String[] TITLE = {"小面包", "依维柯", "小型平板", "中货车", "6米8", "7米6"};
    List<String> TITLe;
    List<String> CarImgList;
    String AddressName;
    OptionsPickerView pvOptions;
    OptionsPickerView pvOptions1;
    List<String> mOptionsItems, mOptionsItems1;

    /**
     * GPS权限
     */
    private int GPS_REQUEST_CODE = 10;
    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };
    /**
     * 位置权限
     */
    private static final int PERMISSON_REQUESTCODE = 0;
    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;
    private boolean isGPS = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayout() {
        return R.layout.activity_shipments;
    }

    @OnClick({R.id.rb_is, R.id.rb_no, R.id.tv_confirm, R.id.tv_handling_type, R.id.img_back, R.id.tv_date, R.id.tv_che_type, R.id.tv_pay_type, R.id.img_back_start})
    void onClicks(View v) {
        switch (v.getId()) {
            case R.id.rb_is:
                if (mRbIsPhone.isChecked()) {
                    isPhone = 1;
                    mEtDriver.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rb_no:
                if (mRbNoPhone.isChecked()) {
                    isPhone = 0;
                    mEtDriver.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_confirm:
                orderInfo();
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_date:
                pvTime.show();
                break;
            case R.id.tv_che_type:
                Intent intent = new Intent(this, VehicleLengthActivity.class);
                intent.putExtra(Constants.ACTIVITY_TYPE, Constants.SHIPMENTS);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_pay_type:
                pvOptions.show();
                break;
            case R.id.tv_handling_type:
                pvOptions1.show();
                break;
            case R.id.img_back_start:
                aMap.setMyLocationEnabled(true);
                break;

        }
    }


    @Override
    protected void initEventAndData() {

        if (mRbIsPhone.isChecked()) {
            isPhone = 1;
            mEtDriver.setVisibility(View.VISIBLE);
        }

        TITLe = new ArrayList<>();
        CarImgList = new ArrayList<>();
        mPresenter.getUserCarModel();
        onLocationClientOption();
        mTvTitle.setText("发货");

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mTvDate.setText(DataUtil.DateToString(date, DataUtil.DATE_TO_STRING_SHORT_PATTERN));
            }
        }).build();

        mOptionsItems1 = new ArrayList<>();
        mOptionsItems1.add("一装一卸");
        mOptionsItems1.add("一装两卸");
        mOptionsItems1.add("一装多卸");
        mOptionsItems1.add("两装一卸");
        mOptionsItems1.add("两装两卸");
        mOptionsItems1.add("多装多卸");
        //选择器
        mOptionsItems = new ArrayList<>();
        mOptionsItems.add("现付");
        mOptionsItems.add("货到付款");
        mOptionsItems.add("回付");
        mOptionsItems.add("月结");
        mOptionsItems.add("多笔付");
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                mTvPayType.setText(mOptionsItems.get(options1));
            }
        }).build();
        pvOptions.setPicker(mOptionsItems);

        //装货方式选择器
        pvOptions1 = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                Log.i(TAG, "onOptionsSelect: " + mOptionsItems1.get(options1));
                mTvLoadMethod.setText(mOptionsItems1.get(options1));
            }
        }).build();
        pvOptions1.setPicker(mOptionsItems1);
    }


    @Override
    public void UserCarModel(List<CarModelEntity> e) {
        Log.i(TAG, "UserCarModel: " + e.size());
        for (int i = 0; i < e.size(); i++) {
            TITLe.add(e.get(i).getCarModel());
            CarImgList.add(Constants.MAIN_URL + e.get(i).getCarImg());
            Log.i(TAG, "UserCarModel: " + e.get(i).getCarModel());
        }
        Log.i(TAG, "UserCarModel: " + TITLe.size() + TITLe.get(0));
        fragmentList = new ArrayList<>();
        fragmentList.clear();
        for (int i = 0; i < TITLe.size(); i++) {
//            Bundle bundle = new Bundle();
//            bundle.putString("carModel", TITLE.get(i));
//            bundle.putString("carImg", CarImgList.get(i));
//            CarTypeFragment carTypeFragment = new CarTypeFragment();
//            carTypeFragment.setArguments(bundle);
            fragmentList.add(new CarTypeFragment());
        }

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, TITLe);
        mVp.setOffscreenPageLimit(3);
        mVp.setAdapter(mViewPagerAdapter);
        mStTab.setViewPager(mVp);
        //   TITLE=e.get(i).getCarModel();
    }

    @Override
    public void UserCarModelErr(String s) {
        Log.i(TAG, "UserCarModelErr: " + s);
    }


    //提交发货单
    @SuppressLint("CheckResult")
    private void orderInfo() {

        Uploading.SendOrder(mLoginPhone, mEtAddress.getText().toString(), mEtEndAddress.getText().toString(), mEtGoods.getText().toString(),
                mEtVolume.getText().toString(), mEtWeight.getText().toString(), mEtPieces.getText().toString(),
                mTvDate.getText().toString(), mTvCheType.getText().toString(), mTvPayType.getText().toString(),
                mTvLoadMethod.getText().toString(), mEtReceipt.getText().toString(), mEtRemark.getText().toString(), mEtEstimatedCost.getText().toString(),
                2, isPhone, mEtDriver.getText().toString(), new SingleObserver<ApiResponse<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ApiResponse<Integer> integer) {
                        switch (integer.getMsg()) {
                            case 0:
                                ToastUtils.show("下单失败");
                                break;
                            case 1:
                                ToastUtils.show("下单成功");
                                finish();
                                break;
                            case -1:
                                ToastUtils.show("参数不能为空");
                                break;
                            default:
                                ToastUtils.show("提交失败" + integer.getMsg());
                                break;

                        }
                        Log.i(TAG, "onSuccess: " + integer.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //  ToastUtils.show("提交失败" + e.getMessage());
                        Log.i(TAG, "onError: " + e.getMessage());
                    }
                });
        // Map<String, RequestBody> map = new HashMap<>();
//        map.put("userAccount", FileUploadUtil.requestBody(mLoginPhone));
//        map.put("startUserName", FileUploadUtil.requestBody());
//        map.put("startPhone", FileUploadUtil.requestBody());
//        map.put("startAddress", FileUploadUtil.requestBody());
//        map.put("endUserName", FileUploadUtil.requestBody());
//        map.put("endPhone", FileUploadUtil.requestBody());
//        map.put("endAddress", FileUploadUtil.requestBody());
//        map.put("freight", FileUploadUtil.requestBody());
//        map.put("loadingTime", FileUploadUtil.requestBody());
//        map.put("loadMethod", FileUploadUtil.requestBody());
//        map.put("agentFee", FileUploadUtil.requestBody());
//        map.put("carModel", FileUploadUtil.requestBody());
//        map.put("payType", FileUploadUtil.requestBody());
//        map.put("totalFee", FileUploadUtil.requestBody());
//        map.put("driverDeposit", FileUploadUtil.requestBody());
//        map.put("receipt", FileUploadUtil.requestBody());
//        map.put("driverName", FileUploadUtil.requestBody());
//        map.put("driverPhone", FileUploadUtil.requestBody());
//
//        map.put("orderNo", FileUploadUtil.requestBody());
//        ApiService.getInstance()
//                .create(UserService.class, Constants.MAIN_URL)
//                .orderInfo(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new SingleObserver<ApiResponse<Integer>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        //  Log.i(TAG, "onSubscribe: ");
//                    }
//
//                    @Override
//                    public void onSuccess(ApiResponse<Integer> integer) {
//                        switch (integer.getMsg()) {
//                            case 0:
//                                ToastUtils.show("上传失败");
//                                break;
//                            case 1:
//                                ToastUtils.show("上传成功");
//                                break;
//                            case -1:
//                                ToastUtils.show("参数不能为空");
//                                break;
//                            case -2:
//                                ToastUtils.show("账号已经注册过");
//                                break;
//                        }
//                        Log.i(TAG, "onSuccess: " + integer.getMsg());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i(TAG, "onError: ");
//                    }
//
//                });
    }

    @Override
    public void UserCarLength(List<CarLengthEntity> e) {

    }

    @Override
    public void UserCarLengthErr(String err) {

    }


    void onLocationClientOption() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style

        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//只定位一次。
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
                mEtAddress.setText(AddressName);
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
        CameraPosition update = new CameraPosition.Builder()
                .target(arg0)
                .zoom(18)
                .build();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(update);
        aMap.animateCamera(cameraUpdate);

        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.ic_coordinate);
        MarkerOptions option = new MarkerOptions().position(arg0).icon(mCurrentMarker);
        // 在地图上添加Marker，并显示
        option.setFlat(true);//设置marker平贴地图效果
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
                mEtAddress.setText(AddressName);
                LatLng cenpt = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());

                CameraPosition update = new CameraPosition.Builder()
                        .target(cenpt)
                        .zoom(18)
                        .build();

                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(update);
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
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        if (mMapView != null) {
            mMapView.onDestroy();
        }

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {

            if (isNeedCheck) {
                checkPermissions(needPermissions);
            }
            //检测是否打开了gps设置
            checkGPSIsOpen();
            if (!isGPS) {
                openGPSSettings();
            }
        }
        onLocationClientOption();
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                mTvCheType.setText(data.getStringExtra(Constants.VEHICLE_TYPE));
                Log.i("onActivityResult", "onActivityResult: " + data.getStringExtra("vehicleLength"));
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    //***********************************************定位权限*********************************************************************

    /**
     * @param permissions
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        try {
            if (Build.VERSION.SDK_INT >= 23
                    && getApplicationInfo().targetSdkVersion >= 23) {
                List<String> needRequestPermissonList = findDeniedPermissions(permissions);
                if (null != needRequestPermissonList
                        && needRequestPermissonList.size() > 0) {
                    String[] array = needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]);
                    Method method = getClass().getMethod("requestPermissions", new Class[]{String[].class,
                            int.class});

                    method.invoke(this, array, PERMISSON_REQUESTCODE);
                }
            }
        } catch (Throwable e) {
        }
    }


    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            try {
                for (String perm : permissions) {
                    Method checkSelfMethod = getClass().getMethod("checkSelfPermission", String.class);
                    Method shouldShowRequestPermissionRationaleMethod = getClass().getMethod("shouldShowRequestPermissionRationale",
                            String.class);
                    if ((Integer) checkSelfMethod.invoke(this, perm) != PackageManager.PERMISSION_GRANTED
                            || (Boolean) shouldShowRequestPermissionRationaleMethod.invoke(this, perm)) {
                        needRequestPermissonList.add(perm);
                    }
                }
            } catch (Throwable e) {

            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否所有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("定位权限");
        builder.setMessage("开启定位权限");

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                });

        builder.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }


    /**
     * 检测GPS是否打开
     *
     * @return
     */
    private void checkGPSIsOpen() {
        LocationManager locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        isGPS = locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
    }

    /**
     * 跳转GPS设置
     */
    private void openGPSSettings() {
        //没有打开则弹出对话框
        new AlertDialog.Builder(this)
                .setTitle(R.string.notifyTitle)
                .setMessage(R.string.gpsNotifyMsg)
                // 拒绝, 退出应用
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })

                .setPositiveButton(R.string.setting,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //跳转GPS设置界面
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivityForResult(intent, GPS_REQUEST_CODE);
                            }
                        })

                .setCancelable(false)
                .show();

    }

    //gps获取手机
    //网络获取手机位置
//        boolean b = hasPermission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
////        if (!b) {
////            requestPermission(Constants.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
////        }

    /**
     * 申请指定的权限.
     */
//    public void requestPermission(int requestCode, String... permissions) {
//
//        ActivityCompat.requestPermissions(this, permissions, requestCode);
//    }


    /**
     * 判断是否有指定的权限
     */
//    public boolean hasPermission(String... permissions) {
//
//        for (String permisson : permissions) {
//            if (ActivityCompat.checkSelfPermission(App.getInstance(), permisson)
//                    != PackageManager.PERMISSION_GRANTED) {
//                return false;
//            }
//        }
//        return true;
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//        } else {
//            showMissingPermissionDialog();
//            ToastUtils.show("您已拒绝使用定位功能");
//        }
//
//
//    }

}
