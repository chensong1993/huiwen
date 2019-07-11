package com.shanghai.logistics.ui.mian_fragment;


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
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerAdapter;
import com.shanghai.logistics.ui.mian_fragment.dynamic.AboutMeFragment;
import com.shanghai.logistics.ui.mian_fragment.dynamic.DynamicViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 动态
 */
public class DynamicFragment extends SimpleFragment {
    @BindView(R.id.tv_head_title)
    TextView mTvHeadTitle;
    @BindView(R.id.img_search)
    ImageView mImgSearch;
    @BindView(R.id.st_tab)
    SlidingTabLayout mStTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    ViewPagerAdapter mViewPagerAdapter;
    List<Fragment> fragmentList;
    String[] TITLE = {"动态", "关于我"};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void initEventAndData() {
        mTvHeadTitle.setText("动态");
       // mImgSearch.setVisibility(View.GONE);
        fragmentList = new ArrayList<>();
        fragmentList.clear();
        fragmentList.add(new DynamicViewPagerFragment());
        fragmentList.add(new AboutMeFragment());
        mViewPagerAdapter = new ViewPagerAdapter(this.getChildFragmentManager(), fragmentList, TITLE);
        mVp.setOffscreenPageLimit(2);
        mVp.setAdapter(mViewPagerAdapter);
        mStTab.setViewPager(mVp);
    }

    @Override
    public void initImmersionBar() {

    }
}
