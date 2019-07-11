package com.shanghai.logistics.ui.logistics_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.PlaceAnOrderActivity;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 开单
 * */
public class BillingActivity extends SimpleActivity {

    @BindView(R.id.ll_waiting_ruku)
    LinearLayout mLlWaitingRuku;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;

    @Override
    protected int getLayout() {
        return R.layout.activity_billing;
    }

    @OnClick({R.id.img_back, R.id.ll_waiting_ruku})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.ll_waiting_ruku:
                Intent intent = new Intent(this, PlaceAnOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.ACTIVITY_TYPE, Constants.BILLING_ACTIVITY);
                intent.putExtra(Constants.ACTIVITY_TYPE, bundle);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("待入库");
    }
}
