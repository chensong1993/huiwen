package com.shanghai.logistics.ui.logistics_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.user_activity.order.OrderDetailActivity;
import com.shanghai.logistics.util.DataUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class StartRecordingActivity extends SimpleActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    TimePickerView pvTime;
    @BindView(R.id.tv_select_starting_date)
    TextView mTvSelectStarDate;
    @BindView(R.id.tv_select_destination_date)
    TextView mTvSelectDestinationDate;
    @Override
    protected int getLayout() {
        return R.layout.activity_start_recording;
    }

    @OnClick({R.id.img_back, R.id.tv_select_starting_date, R.id.tv_select_destination_date, R.id.ll_orders})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_select_starting_date:
                pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTvSelectStarDate.setText(DataUtil.DateToString(date, DataUtil.DATE_TO_STRING_SHORT_PATTERN));
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.tv_select_destination_date:
                pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTvSelectDestinationDate.setText(DataUtil.DateToString(date, DataUtil.DATE_TO_STRING_SHORT_PATTERN));
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.ll_orders:
                startActivity(new Intent(this, LogisticsDetailsActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("发车记录");
    }
}
