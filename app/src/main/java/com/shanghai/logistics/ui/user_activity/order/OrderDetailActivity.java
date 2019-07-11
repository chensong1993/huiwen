package com.shanghai.logistics.ui.user_activity.order;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerAdapter;
import com.shanghai.logistics.ui.user_fragment.order.EAWBFragment;
import com.shanghai.logistics.ui.user_fragment.order.TakeOrdersFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailActivity extends SimpleActivity {

    @BindView(R.id.st_tab)
    SlidingTabLayout mStTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    ViewPagerAdapter mViewPagerAdapter;
    List<Fragment> fragmentList;
    String[] TITLE = {"物流详情", "签收回单", "电子运单"};

    @Override
    protected int getLayout() {
        return R.layout.activity_order_detail;
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
//        mStTab.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelect(int position) {
//
//            }
//
//            @Override
//            public void onTabReselect(int position) {
//
//            }
//        });
        mTvTitle.setText("物流详情");
        fragmentList = new ArrayList<>();
        fragmentList.clear();
        fragmentList.add(new EAWBFragment());
        fragmentList.add(new EAWBFragment());
        fragmentList.add(new EAWBFragment());
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, TITLE);
        mVp.setOffscreenPageLimit(3);
        mVp.setAdapter(mViewPagerAdapter);
        mStTab.setViewPager(mVp);
    }
}
