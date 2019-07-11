package com.shanghai.logistics.ui.logistics_activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerAdapter;
import com.shanghai.logistics.ui.driver_fragment.order.DriverTakeOrdersFragment;
import com.shanghai.logistics.ui.logistics_fragment.home.ShippingDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 物流详情
 * */
public class LogisticsDetailsActivity extends SimpleActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.st_tab)
    SlidingTabLayout mStTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    ViewPagerAdapter mViewPagerAdapter;
    List<Fragment> fragmentList;
    String[] TITLE = {"货物清单", "物流详情", "签收订单", "运输详情"};

    @Override
    protected int getLayout() {
        return R.layout.activity_logistics_details;
    }

    @OnClick({R.id.img_back})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }


    @Override
    protected void initEventAndData() {
        mTvTitle.setText("物流详情");

        fragmentList = new ArrayList<>();
        fragmentList.clear();
        fragmentList.add(new DriverTakeOrdersFragment());
        fragmentList.add(new DriverTakeOrdersFragment());
        fragmentList.add(new DriverTakeOrdersFragment());
        fragmentList.add(new ShippingDetailsFragment());
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, TITLE);
        mVp.setOffscreenPageLimit(4);
        mVp.setAdapter(mViewPagerAdapter);
        mStTab.setViewPager(mVp);
    }
}
