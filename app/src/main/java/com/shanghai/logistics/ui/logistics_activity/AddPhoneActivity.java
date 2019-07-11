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

public class AddPhoneActivity extends SimpleActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_phone;
    }

    @OnClick({R.id.et_ps, R.id.img_back, R.id.et_phone, R.id.tv_save})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_save:
                finish();
                break;

        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("添加号码");
    }
}
