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

/*
 * 电话列表
 * */
public class PhoneListActivity extends SimpleActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_add_phone)
    TextView mTvAddPhone;

    @Override
    protected int getLayout() {
        return R.layout.activity_phone_list;
    }

    @OnClick({R.id.tv_add_phone, R.id.img_back, R.id.tv_call})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_add_phone:
                startActivityForResult(new Intent(this, AddPhoneActivity.class), 0);
                break;
            case R.id.tv_call:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "1835245875");
                intent.setData(data);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void initEventAndData() {

    }
}
