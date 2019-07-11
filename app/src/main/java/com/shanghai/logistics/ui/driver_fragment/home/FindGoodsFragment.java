package com.shanghai.logistics.ui.driver_fragment.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.view.TimePickerView;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 找货
 */
public class FindGoodsFragment extends SimpleFragment {

    @BindView(R.id.tv_select_starting_point)
    TextView mTvSelectStartingPoint;
    @BindView(R.id.tv_select_destination)
    TextView mTvSelectDestination;
    //地区对象
    CityPickerView mPicker;
    CityConfig cityConfig;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find_goods;
    }

    @OnClick({R.id.tv_select_starting_point,R.id.tv_select_destination})
    void onClick(View v){
        switch (v.getId()){
            case R.id.tv_select_starting_point:
                cityConfig = new CityConfig.Builder().build();
                mPicker.setConfig(cityConfig);
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        mTvSelectStartingPoint.setText(province + "-" + city + "-" + district);
                    }

                    @Override
                    public void onCancel() {
                    }
                });

                //显示
                mPicker.showCityPicker();
                break;
            case R.id.tv_select_destination:
                cityConfig = new CityConfig.Builder().build();
                mPicker.setConfig(cityConfig);
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        mTvSelectDestination.setText(province + "-" + city + "-" + district);
                    }

                    @Override
                    public void onCancel() {
                    }
                });
                mPicker.showCityPicker();
                break;
        }
    }
    @Override
    protected void initEventAndData() {
        mPicker = new CityPickerView();
        mPicker.init(getActivity());
    }

    @Override
    public void initImmersionBar() {

    }
}
