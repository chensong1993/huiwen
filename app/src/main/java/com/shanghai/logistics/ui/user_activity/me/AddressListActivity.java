package com.shanghai.logistics.ui.user_activity.me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.AddressActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressListActivity extends SimpleActivity {

    @BindView(R.id.tv_right_content)
    TextView mTvRightText;
    @BindView(R.id.img_back)
    ImageView mImgBack;

    @Override
    protected int getLayout() {
        return R.layout.activity_address_list;
    }

    @OnClick({R.id.img_back, R.id.tv_right_content})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right_content:
                startActivity(new Intent(this, AddAddressActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvRightText.setVisibility(View.VISIBLE);
        mTvRightText.setText("添加地址");
    }
}
