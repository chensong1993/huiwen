package com.shanghai.logistics.ui.user_activity.me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.style.cityjd.JDCityConfig;
import com.lljjcoder.style.cityjd.JDCityPicker;
import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AddAddressActivity extends SimpleActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    JDCityPicker cityPicker;
    @Override
    protected int getLayout() {
        return R.layout.activity_add_address;
    }

    @OnClick({R.id.img_back, R.id.tv_confirm, R.id.tv_area})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_confirm:
                finish();
                break;
            case R.id.tv_area:
                JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();
                jdCityConfig.setShowType(JDCityConfig.ShowType.PRO_CITY_DIS);
                cityPicker.setConfig(jdCityConfig);
                cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        if (city.getName().equals("省直辖县级行政单位")) {
                            mTvArea.setText(province.getName()+"-"+district.getName());
                        } else {
                            mTvArea.setText(province.getName()+"-"+city.getName()+"-"+district.getName());
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
        mTvTitle.setText("地址编辑");
        cityPicker = new JDCityPicker();
        cityPicker.init(this);
    }
}
