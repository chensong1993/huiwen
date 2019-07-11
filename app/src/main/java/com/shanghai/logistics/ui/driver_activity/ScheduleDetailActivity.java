package com.shanghai.logistics.ui.driver_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.user_activity.release.ReleaseDetailActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ScheduleDetailActivity extends SimpleActivity {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_schedule_detail;
    }

    @OnClick({R.id.img_back})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;

        }

    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("行程详情");
    }
}
