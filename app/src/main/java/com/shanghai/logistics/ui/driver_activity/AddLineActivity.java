package com.shanghai.logistics.ui.driver_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.VehicleLengthActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddLineActivity extends SimpleActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_origin)
    TextView mTvOrigin;
    @BindView(R.id.tv_destination)
    TextView mTvDestination;
    @BindView(R.id.tv_che_type)
    TextView mTvCheType;
    //地区对象
    CityPickerView mPicker;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_line;
    }

    @OnClick({R.id.img_back, R.id.tv_che_type, R.id.tv_origin, R.id.tv_destination, R.id.tv_confirm})
    void onClicks(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_confirm:
                finish();
                break;
            case R.id.tv_che_type:
                Intent intent = new Intent(this, VehicleLengthActivity.class);
                intent.putExtra(Constants.ACTIVITY_TYPE, Constants.ADD_LINE);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_origin:
                CityConfig cityConfig = new CityConfig.Builder().build();
                mPicker.setConfig(cityConfig);
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        mTvOrigin.setText(province + "-" + city + "-" + district);
                    }

                    @Override
                    public void onCancel() {
                    }
                });

                //显示
                mPicker.showCityPicker();
                break;
            case R.id.tv_destination:
                CityConfig cityConfig1 = new CityConfig.Builder().build();
                mPicker.setConfig(cityConfig1);

                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        mTvDestination.setText(province + "-" + city + "-" + district);
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
        mPicker.init(this);
        mTvTitle.setText("添加常线路");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                mTvCheType.setText(data.getStringExtra(Constants.VEHICLE_TYPE));
                break;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
