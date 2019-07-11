package com.shanghai.logistics.ui.logistics_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.cityjd.JDCityConfig;
import com.lljjcoder.style.cityjd.JDCityPicker;
import com.lljjcoder.style.citylist.CityListSelectActivity;
import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerAdapter;
import com.shanghai.logistics.ui.driver_fragment.home.FindGoodsFragment;
import com.shanghai.logistics.ui.driver_fragment.home.MyLineFragment;
import com.shanghai.logistics.ui.driver_fragment.home.NewOrdersFragment;
import com.shanghai.logistics.ui.logistics_fragment.home.OperatingCenterFragment;
import com.shanghai.logistics.ui.user_fragment.UserHomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 物流端首页
 */
public class LogisticsHomeFragment extends SimpleFragment {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_right_content)
    TextView mTvRightTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.st_tab)
    SlidingTabLayout mStTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    ViewPagerAdapter mViewPagerAdapter;
    List<Fragment> fragmentList;
    String[] TITLE = {"运营中心", "物流商城"};
    JDCityPicker cityPicker;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_logistics_home;
    }


    @OnClick({R.id.img_back, R.id.tv_right_content})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.tv_right_content:
                JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();
                jdCityConfig.setShowType(JDCityConfig.ShowType.PRO_CITY);
                cityPicker.setConfig(jdCityConfig);
                cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        if (city.getName().equals("省直辖县级行政单位")) {
                            mTvRightTitle.setText(province.getName());
                        } else {
                            mTvRightTitle.setText(city.getName());
                        }

                    }

                    @Override
                    public void onCancel() {
                    }
                });
                cityPicker.showCityPicker();
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("首页");
        mTvRightTitle.setText("地区");
        cityPicker = new JDCityPicker();
        cityPicker.init(getActivity());
        mTvRightTitle.setVisibility(View.VISIBLE);
        fragmentList = new ArrayList<>();
        fragmentList.clear();
        UserHomeFragment userHomeFragment = new UserHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ACTIVITY_TYPE, Constants.USER_HOME_FRAGMENT);
        userHomeFragment.setArguments(bundle);
        fragmentList.add(new OperatingCenterFragment());
        fragmentList.add(userHomeFragment);
        mViewPagerAdapter = new ViewPagerAdapter(this.getChildFragmentManager(), fragmentList, TITLE);
        mVp.setOffscreenPageLimit(2);
        mVp.setAdapter(mViewPagerAdapter);
        mStTab.setViewPager(mVp);
    }


    @Override
    public void initImmersionBar() {

    }
}
