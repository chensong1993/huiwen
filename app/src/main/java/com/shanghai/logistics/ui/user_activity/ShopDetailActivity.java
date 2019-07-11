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

import com.flyco.tablayout.SlidingTabLayout;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerAdapter;
import com.shanghai.logistics.ui.fragment.BusinessesFragment;
import com.shanghai.logistics.ui.fragment.ProductServiceFragment;
import com.shanghai.logistics.ui.logistics_activity.AddShopActivity;
import com.shanghai.logistics.ui.mian_fragment.AddressListFragment;
import com.shanghai.logistics.ui.mian_fragment.dynamic.AboutMeFragment;
import com.shanghai.logistics.ui.mian_fragment.dynamic.DynamicViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 品牌物流详情
 * */
public class ShopDetailActivity extends SimpleActivity {
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.img_call)
    ImageView mImgCall;
    @BindView(R.id.st_tab)
    SlidingTabLayout mStTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.ll_shop)
    LinearLayout mLlShop;
    ViewPagerAdapter mViewPagerAdapter;
    List<Fragment> fragmentList;
    String[] TITLE = {"产品服务", "商家信息"};
    static final String TAG = "ShopDetailActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void initEventAndData() {
        //根据不同的主体选择不同的样式
        Bundle bundle = getIntent().getBundleExtra(Constants.ACTIVITY_TYPE);
        ProductServiceFragment productServiceFragment = new ProductServiceFragment();
        fragmentList = new ArrayList<>();
        fragmentList.clear();
        if (bundle != null) {
            int type = bundle.getInt(Constants.ACTIVITY_TYPE);
            productServiceFragment.setArguments(bundle);
            Log.i(TAG, "initEventAndData: " + type);
            switch (type) {
                case Constants.LOGISTICS_ME_FRAGMENT:
                    mLlShop.setVisibility(View.VISIBLE);
                    fragmentList.add(productServiceFragment);
                    fragmentList.add(new BusinessesFragment());
                    break;
                case Constants.USER_BACK_ACTIVITY:
                    mLlShop.setVisibility(View.GONE);
                    fragmentList.add(productServiceFragment);
                    fragmentList.add(new BusinessesFragment());
                    break;

            }
        }

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, TITLE);
        mVp.setOffscreenPageLimit(2);
        mVp.setAdapter(mViewPagerAdapter);
        mStTab.setViewPager(mVp);
    }

    @OnClick({R.id.img_back, R.id.img_call, R.id.tv_shop_manage, R.id.tv_add_manage})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_call:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "17621147941");
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.tv_shop_manage:

                break;
            case R.id.tv_add_manage:
                startActivity(new Intent(this, AddShopActivity.class));
                break;

        }

    }


}
