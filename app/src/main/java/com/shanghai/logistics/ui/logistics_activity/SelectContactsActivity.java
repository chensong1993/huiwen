package com.shanghai.logistics.ui.logistics_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectContactsActivity extends SimpleActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_confirm)
    TextView mTvConFirm;
    @BindView(R.id.et_contacts)
    EditText mEtContacts;

    @Override
    protected int getLayout() {
        return R.layout.activity_select_contacts;
    }

    @OnClick({R.id.img_back, R.id.tv_confirm})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_confirm:
                Intent intent = new Intent(this, OrderActivity.class);
                setResult(0, intent);
                finish();
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("选择联系人");
    }
}
