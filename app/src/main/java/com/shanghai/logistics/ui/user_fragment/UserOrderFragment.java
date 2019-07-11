package com.shanghai.logistics.ui.user_fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerAdapter;
import com.shanghai.logistics.ui.mian_fragment.dynamic.AboutMeFragment;
import com.shanghai.logistics.ui.mian_fragment.dynamic.DynamicViewPagerFragment;
import com.shanghai.logistics.ui.user_activity.order.OrderDetailActivity;
import com.shanghai.logistics.ui.user_fragment.order.TakeOrdersFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 订单
 * */

public class UserOrderFragment extends SimpleFragment {
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
    String[] TITLE = {"我收的", "我发的", "历史订单"};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
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
        mTvTitle.setText("订单");
        fragmentList = new ArrayList<>();
        fragmentList.clear();
        fragmentList.add(new TakeOrdersFragment());
        fragmentList.add(new TakeOrdersFragment());
        fragmentList.add(new TakeOrdersFragment());
        mViewPagerAdapter = new ViewPagerAdapter(this.getChildFragmentManager(), fragmentList, TITLE);
        mVp.setOffscreenPageLimit(3);
        mVp.setAdapter(mViewPagerAdapter);
        mStTab.setViewPager(mVp);
    }


    @Override
    public void initImmersionBar() {
//        ImmersionBar.with(this)
//                .statusBarView(mTvStatusBar)
//                .statusBarColor(R.color.black)
//                .init();
    }


}
