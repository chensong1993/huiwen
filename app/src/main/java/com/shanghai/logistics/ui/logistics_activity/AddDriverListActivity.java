package com.shanghai.logistics.ui.logistics_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.VehicleLengthActivity;
import com.shanghai.logistics.util.DataUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class AddDriverListActivity extends SimpleActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_right_content)
    TextView mTvRightTitle;
    @BindView(R.id.tv_starting_point)
    TextView mTvStartingPoint;
    @BindView(R.id.tv_destination)
    TextView mTvDestination;
    @BindView(R.id.tv_chr_type)
    TextView mTvChrType;
    TimePickerView pvTime;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_driver_list;
    }

    @OnClick({R.id.img_back, R.id.tv_right_content, R.id.tv_starting_point, R.id.tv_destination, R.id.tv_chr_type})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right_content:
                break;
            case R.id.tv_starting_point:
                pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTvStartingPoint.setText(DataUtil.DateToString(date, DataUtil.DATE_TO_STRING_SHORT_PATTERN));
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.tv_destination:
                pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTvDestination.setText(DataUtil.DateToString(date, DataUtil.DATE_TO_STRING_SHORT_PATTERN));
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.tv_chr_type:
                Intent intent = new Intent(this, VehicleLengthActivity.class);
                intent.putExtra(Constants.ACTIVITY_TYPE, Constants.ADD_DRIVER_LIST_ACTIVITY);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvRightTitle.setVisibility(View.VISIBLE);
        mTvRightTitle.setText("添加");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                mTvChrType.setText(data.getStringExtra(Constants.VEHICLE_TYPE));
                Log.i("onActivityResult", "onActivityResult: " + data.getStringExtra("vehicleLength"));
                break;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
