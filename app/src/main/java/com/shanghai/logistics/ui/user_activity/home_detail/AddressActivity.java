package com.shanghai.logistics.ui.user_activity.home_detail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressActivity extends SimpleActivity {

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.cb_select_address)
    CheckBox mCbSelectAddress;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    Bundle bundle;
    static final String TAG = "AddressActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_address;
    }

    @OnClick({R.id.cb_select_address, R.id.img_back, R.id.tv_confirm})
    void onClicks(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_confirm:
                if (mCbSelectAddress.isChecked()) {
                    Intent intent = new Intent(this, PlaceAnOrderActivity.class);
                    Log.i(TAG, mTvName.getText().toString());
                    bundle = new Bundle();
                    bundle.putString("address_name", mTvName.getText().toString());
                    bundle.putString("address_phone", mTvPhone.getText().toString());
                    bundle.putString("address_address", mTvAddress.getText().toString());
                    intent.putExtra("addressActivity", bundle);
                    setResult(PlaceAnOrderActivity.ADDRESS, intent);
                    finish();

                }
                break;
        }
    }

    @Override
    protected void initEventAndData() {

    }
}
