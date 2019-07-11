package com.shanghai.logistics.ui.driver_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerAdapter;
import com.shanghai.logistics.ui.driver_fragment.home.FindGoodsFragment;
import com.shanghai.logistics.ui.driver_fragment.home.MyLineFragment;
import com.shanghai.logistics.ui.driver_fragment.home.NewOrdersFragment;
import com.shanghai.logistics.ui.user_fragment.order.TakeOrdersFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 车主 首页
 */
public class DriverHomeFragment extends SimpleFragment {

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
    String[] TITLE = {"新订单", "我的线路", "找货"};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_driver_home;
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
        mTvTitle.setText("首页");
        fragmentList = new ArrayList<>();
        fragmentList.clear();
        fragmentList.add(new NewOrdersFragment());
        fragmentList.add(new MyLineFragment());
        fragmentList.add(new FindGoodsFragment());
        mViewPagerAdapter = new ViewPagerAdapter(this.getChildFragmentManager(), fragmentList, TITLE);
        mVp.setOffscreenPageLimit(3);
        mVp.setAdapter(mViewPagerAdapter);
        mStTab.setViewPager(mVp);
    }

    @Override
    public void initImmersionBar() {

    }
}
