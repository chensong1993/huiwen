package com.shanghai.logistics.ui.logistics_activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 接单人
 * */
public class OrderActivity extends SimpleActivity {

    @BindView(R.id.tv_select_add)
    TextView mTvSelectAdd;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_order;
    }

    @OnClick({R.id.img_back, R.id.tv_select_add})
    void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_select_add:
                startActivityForResult(new Intent(this, SelectContactsActivity.class), 0);
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("接单人");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
