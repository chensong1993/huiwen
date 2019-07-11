package com.shanghai.logistics.ui.driver_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerAdapter;
import com.shanghai.logistics.ui.user_activity.release.SendOrdersActivity;
import com.shanghai.logistics.ui.user_fragment.linkman.CarsFragment;
import com.shanghai.logistics.ui.user_fragment.linkman.ScheduleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DriverMeFragment extends SimpleFragment {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.st_tab)
    SlidingTabLayout mStTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    ViewPagerAdapter mViewPagerAdapter;
    List<Fragment> fragmentList;
    String[] TITLE = {"行程", "车辆"};


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_driver_me;
    }

    @OnClick({R.id.img_back})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                getActivity().finish();
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("我的");
        fragmentList = new ArrayList<>();
        fragmentList.clear();
        ScheduleFragment scheduleFragment = new ScheduleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ACTIVITY_TYPE, Constants.DRIVER_ME_FRAGMENT);
        scheduleFragment.setArguments(bundle);

        CarsFragment carsFragment = new CarsFragment();
        carsFragment.setArguments(bundle);

        fragmentList.add(scheduleFragment);
        fragmentList.add(carsFragment);
        mViewPagerAdapter = new ViewPagerAdapter(this.getChildFragmentManager(), fragmentList, TITLE);
        mVp.setOffscreenPageLimit(2);
        mVp.setAdapter(mViewPagerAdapter);
        mStTab.setViewPager(mVp);
    }

    @Override
    public void initImmersionBar() {

    }
}
