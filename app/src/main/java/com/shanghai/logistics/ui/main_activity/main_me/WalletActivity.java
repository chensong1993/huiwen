package com.shanghai.logistics.ui.main_activity.main_me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 我的钱包
 * */
public class WalletActivity extends SimpleActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_pay)
    TextView mTvPay;
    @BindView(R.id.tv_withdraw)
    TextView mTvWithDraw;

    @Override
    protected int getLayout() {
        return R.layout.activity_wallet;
    }

    @OnClick({R.id.img_back, R.id.tv_pay, R.id.tv_withdraw})
    void CollectOnclick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_pay:
                startActivity(new Intent(this, PayActivity.class));
                break;
            case R.id.tv_withdraw:
                startActivity(new Intent(this, WithdrawActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("我的钱包");
    }
}
