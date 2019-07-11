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
import com.shanghai.logistics.ui.logistics_fragment.home.AsternRecordFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 到车记录
 * */
public class AsternRecordActivity extends SimpleActivity {

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

    String[] TITLE = {"待配送", "配送中", "到车记录"};

    @Override
    protected int getLayout() {
        return R.layout.activity_astern_record;
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
        mTvTitle.setText("到车记录");
        fragmentList = new ArrayList<>();
        fragmentList.clear();
        fragmentList.add(new AsternRecordFragment());
        fragmentList.add(new AsternRecordFragment());
        fragmentList.add(new AsternRecordFragment());
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, TITLE);
        mVp.setOffscreenPageLimit(3);
        mVp.setAdapter(mViewPagerAdapter);
        mStTab.setViewPager(mVp);
    }
}
