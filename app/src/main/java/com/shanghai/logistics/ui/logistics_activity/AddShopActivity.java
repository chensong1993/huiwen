package com.shanghai.logistics.ui.logistics_activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AddShopActivity extends SimpleActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_shop;
    }

    @OnClick({R.id.img_back, R.id.ll_order, R.id.ll_phone})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.ll_order:
                startActivity(new Intent(this, OrderActivity.class));
                break;
            case R.id.ll_phone:
                startActivity(new Intent(this, PhoneListActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {

    }
}
