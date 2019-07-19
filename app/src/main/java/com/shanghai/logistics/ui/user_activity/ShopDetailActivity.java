package com.shanghai.logistics.ui.user_activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.base.connectors.user.UserShopDetailConnector;
import com.shanghai.logistics.models.entity.user.UserShopEntity;
import com.shanghai.logistics.presenters.logistics.LogisticsStoreInfoPresenter;
import com.shanghai.logistics.presenters.user.UserShopDetailPresenter;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerAdapter;
import com.shanghai.logistics.ui.fragment.BusinessesFragment;
import com.shanghai.logistics.ui.fragment.ProductServiceFragment;
import com.shanghai.logistics.ui.logistics_activity.AddShopActivity;
import com.shanghai.logistics.ui.mian_fragment.AddressListFragment;
import com.shanghai.logistics.ui.mian_fragment.dynamic.AboutMeFragment;
import com.shanghai.logistics.ui.mian_fragment.dynamic.DynamicViewPagerFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 品牌物流详情
 * */
public class ShopDetailActivity extends BaseActivity<UserShopDetailPresenter> implements UserShopDetailConnector.View, AMapLocationListener {
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.img_store)
    ImageView mImgStore;
    @BindView(R.id.tv_storeName)
    TextView mTvStoreName;
    @BindView(R.id.tv_storeService_point)
    TextView mTvStoreServicePoint;
    @BindView(R.id.tv_store_address)
    TextView mTvStoreAddress;
    @BindView(R.id.tv_introduction)
    TextView mTvIntroduction;
    @BindView(R.id.tv_map_address)
    TextView mTvMapAddress;
    @BindView(R.id.st_tab)
    SlidingTabLayout mStTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.ll_shop)
    LinearLayout mLlShop;
    String mContactPhone, mCurrentPosition;
    ViewPagerAdapter mViewPagerAdapter;
    List<Fragment> fragmentList;
    String[] TITLE = {"产品服务", "商家信息"};
    UserShopEntity userShopEntity;
    private static final String TAG = "ShopDetailActivity";
    Bundle bundle;
    //声明mlocationClient对象
    public AMapLocationClient mLocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption;
    ProductServiceFragment productServiceFragment;
    int id;
    int type;
    @Override
    protected int getLayout() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void initEventAndData() {
        //根据不同的主体选择不同的样式
        bundle = getIntent().getBundleExtra(Constants.ACTIVITY_TYPE);
        if (bundle != null) {
            type = bundle.getInt(Constants.ACTIVITY_TYPE);
            id = bundle.getInt(Constants.STORE_ID);//商铺编号
       //
            switch (type) {
                case Constants.LOGISTICS_ME_FRAGMENT:
                    mLlShop.setVisibility(View.VISIBLE);
                    mPresenter.getUserShopDetail(id + "", 1);
                    break;
                case Constants.USER_BACK_ACTIVITY:
                    mLlShop.setVisibility(View.GONE);
                    mPresenter.getUserShopDetail(id + "", 2);
                    break;

            }
        }

        onPosition();
    }


    @Override
    protected void onResume() {
        super.onResume();
            switch (type) {
                case Constants.LOGISTICS_ME_FRAGMENT:
                    mLlShop.setVisibility(View.VISIBLE);
                    mPresenter.getUserShopDetail(id + "", 1);
                    break;
                case Constants.USER_BACK_ACTIVITY:
                    mLlShop.setVisibility(View.GONE);
                    mPresenter.getUserShopDetail(id + "", 2);
                    break;
            }


    }

    @Override
    public void UserShopDetail(UserShopEntity e) {
        //  Log.i(TAG, "UserShopDetail: " + entities.toString());
       // ToastUtils.show(e.toString());
        mTvStoreName.setText(e.getStoreName());

        mTvStoreServicePoint.setText("综合服务分" + e.getStoreServicePoint() + "");

        mTvStoreAddress.setText(e.getStoreAddress());

        mTvIntroduction.setText(e.getIntroduction());

        String[] strings = e.getStoreImg().split(",");
        String imgUrl = null;
        for (int i = 0; i < strings.length; i++) {
            imgUrl = strings[0];
        }
        Glide.with(this).load(Constants.MAIN_URL + imgUrl).into(mImgStore);
        mContactPhone = e.getContactPhone();

        productServiceFragment = new ProductServiceFragment();
        fragmentList = new ArrayList<>();
        fragmentList.clear();
        bundle = new Bundle();
        bundle.putSerializable(Constants.STORE_LIST, e);
        productServiceFragment.setArguments(bundle);
        fragmentList.add(productServiceFragment);
        fragmentList.add(new BusinessesFragment());
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, TITLE);
        mVp.setOffscreenPageLimit(2);
        mVp.setAdapter(mViewPagerAdapter);
        mStTab.setViewPager(mVp);
    }

    @Override
    public void UserShopDetailErr(String s) {
        Log.i(TAG, "UserShopDetailErr: " + s);
    }

    @OnClick({R.id.img_back, R.id.img_call, R.id.tv_shop_manage, R.id.tv_add_manage})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_call:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + mContactPhone);
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.tv_shop_manage:

                break;
            case R.id.tv_add_manage:
                Intent intent1=new Intent(this, AddShopActivity.class);
                intent1.putExtra(Constants.STORE_ID,id);
                startActivity(intent1);
                break;

        }

    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    void onPosition() {

        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mLocationClient.setLocationListener(this);

        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

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
                mTvMapAddress.setText(amapLocation.getAddress());
                //    AddressName = amapLocation.getAddress();

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }
}
