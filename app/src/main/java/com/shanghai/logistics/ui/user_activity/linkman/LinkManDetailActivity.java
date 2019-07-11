package com.shanghai.logistics.ui.user_activity.linkman;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerAdapter;
import com.shanghai.logistics.ui.user_activity.release.SendOrdersActivity;
import com.shanghai.logistics.ui.user_fragment.linkman.CarsFragment;
import com.shanghai.logistics.ui.user_fragment.linkman.ScheduleFragment;
import com.shanghai.logistics.ui.user_fragment.order.TakeOrdersFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LinkManDetailActivity extends SimpleActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_send_orders)
    TextView mTvSendOrders;
    @BindView(R.id.st_tab)
    SlidingTabLayout mStTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    ViewPagerAdapter mViewPagerAdapter;
    List<Fragment> fragmentList;
    String[] TITLE = {"行程", "车辆"};

    @Override
    protected int getLayout() {
        return R.layout.activity_link_man_detail;
    }

    @OnClick({R.id.img_back, R.id.tv_send_orders})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_send_orders:
                startActivity(new Intent(this, SendOrdersActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("订单详情");
        fragmentList = new ArrayList<>();
        fragmentList.clear();
        fragmentList.add(new ScheduleFragment());
        fragmentList.add(new CarsFragment());
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, TITLE);
        mVp.setOffscreenPageLimit(2);
        mVp.setAdapter(mViewPagerAdapter);
        mStTab.setViewPager(mVp);
    }
}
