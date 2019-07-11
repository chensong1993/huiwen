package com.shanghai.logistics.ui.user_activity.home_detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.driver_activity.AddLineActivity;
import com.shanghai.logistics.ui.logistics_activity.AddDriverListActivity;
import com.shanghai.logistics.ui.logistics_fragment.home.FindGoodsFragment;
import com.shanghai.logistics.ui.user_activity.release.ShipmentsActivity;
import com.shanghai.logistics.ui.user_fragment.UserReleaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 车长
 * */
public class VehicleLengthActivity extends SimpleActivity {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_length)
    CheckBox mCbLength;
    @BindView(R.id.tv_length2)
    CheckBox mCbLength2;
    @BindView(R.id.tv_length3)
    CheckBox mCbLength3;
    @BindView(R.id.tv_length4)
    CheckBox mCbLength4;
    @BindView(R.id.et_length)
    EditText mEtContent;
    @BindView(R.id.tv_length_confirm)
    TextView mTvConfirm;
    //货车类型
    @BindView(R.id.ll_vehicle)
    LinearLayout mLlVehicle;
    @BindView(R.id.ll_v_length)
    LinearLayout mLlVLength;
    //货车
    @BindView(R.id.tv_vehicle)
    TextView mTvVehicle;
    String mCbContent = "";
    String VehicleType;
    int i = 0;
    int ActivityType;
    Intent intent;

    @Override
    protected int getLayout() {
        return R.layout.activity_vehicle_length;
    }

    @OnClick({R.id.img_back, R.id.tv_length, R.id.tv_length2, R.id.tv_length3, R.id.tv_length4, R.id.tv_length_confirm, R.id.ll_vehicle})
    void onClicks(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                if (mLlVehicle.getVisibility() == View.GONE) {
                    mLlVehicle.setVisibility(View.VISIBLE);
                    mTvConfirm.setVisibility(View.GONE);
                    mLlVLength.setVisibility(View.GONE);
                } else {
                    finish();
                }

                break;
            case R.id.ll_vehicle:
                VehicleType = mTvVehicle.getText().toString();
                mLlVehicle.setVisibility(View.GONE);
                mLlVLength.setVisibility(View.VISIBLE);
                mTvConfirm.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_length:
                if (mCbLength.isChecked() && i < 3) {
                    mCbLength.setTextColor(ContextCompat.getColor(this, R.color.red));
                    mCbContent += mCbLength.getText().toString() + "/";
                    i++;
                } else {
                    mCbLength.setTextColor(ContextCompat.getColor(this, R.color.black));
                    mCbContent = mCbContent.replace(mCbLength.getText().toString(), "");
                    i--;
                    ToastUtils.show("最多只能选择三个");
                }
                break;
            case R.id.tv_length2:
                if (mCbLength2.isChecked() && i < 3) {
                    mCbLength2.setTextColor(ContextCompat.getColor(this, R.color.red));
                    mCbContent += mCbLength2.getText().toString() + "/";
                    i++;
                } else {
                    mCbLength2.setTextColor(ContextCompat.getColor(this, R.color.black));
                    mCbContent = mCbContent.replace(mCbLength2.getText().toString(), "");
                    i--;
                    ToastUtils.show("最多只能选择三个");
                }

                break;
            case R.id.tv_length3:
                if (mCbLength3.isChecked() && i < 3) {
                    mCbLength3.setTextColor(ContextCompat.getColor(this, R.color.red));
                    mCbContent += mCbLength3.getText().toString() + "/";
                    i++;
                } else {
                    mCbLength3.setTextColor(ContextCompat.getColor(this, R.color.black));
                    mCbContent = mCbContent.replace(mCbLength3.getText().toString(), "");
                    i--;
                    ToastUtils.show("最多只能选择三个");
                }

                break;
            case R.id.tv_length4:
                if (mCbLength4.isChecked() && i < 3) {
                    mCbLength4.setTextColor(ContextCompat.getColor(this, R.color.red));
                    mCbContent += mCbLength4.getText().toString() + "/";
                    i++;
                } else {
                    mCbLength4.setTextColor(ContextCompat.getColor(this, R.color.black));
                    mCbContent = mCbContent.replace(mCbLength4.getText().toString(), "");
                    i--;
                    ToastUtils.show("最多只能选择三个");
                }
                break;
            case R.id.tv_length_confirm:
                switch (ActivityType) {
                    case Constants.SEND_ORDERS:
                        intent = new Intent(this, UserReleaseFragment.class);
                        intent.putExtra(Constants.VEHICLE_TYPE, VehicleType + mCbContent + mEtContent.getText().toString());
                        setResult(1, intent);
                        finish();
                        break;
                    case Constants.PlaceAnOrder:
                        intent = new Intent(this, PlaceAnOrderActivity.class);
                        intent.putExtra(Constants.VEHICLE_TYPE, VehicleType + mCbContent + mEtContent.getText().toString());
                        setResult(PlaceAnOrderActivity.VEHICLE_TYPE, intent);
                        finish();
                        break;
                    case Constants.SHIPMENTS:
                        intent = new Intent(this, ShipmentsActivity.class);
                        intent.putExtra(Constants.VEHICLE_TYPE, VehicleType + mCbContent + mEtContent.getText().toString());
                        setResult(1, intent);
                        finish();
                    case Constants.ADD_LINE:
                        intent = new Intent(this, AddLineActivity.class);
                        intent.putExtra(Constants.VEHICLE_TYPE, VehicleType + mCbContent + mEtContent.getText().toString());
                        setResult(1, intent);
                        finish();
                        break;
                    case Constants.ADD_DRIVER_LIST_ACTIVITY:
                        intent = new Intent(this, AddDriverListActivity.class);
                        intent.putExtra(Constants.VEHICLE_TYPE, VehicleType + mCbContent + mEtContent.getText().toString());
                        setResult(1, intent);
                        finish();
                        break;
                    case Constants.FIND_GOODS_FRAGMENT2:
                        intent = new Intent(this, FindGoodsFragment.class);
                        intent.putExtra(Constants.VEHICLE_TYPE, VehicleType + mCbContent + mEtContent.getText().toString());
                        setResult(1, intent);
                        finish();
                        break;
                }

                // Log.i("tv_length_confirm", i + "tv_length_confirm: " + mCbContent);
                break;

        }

    }

    @Override
    protected void initEventAndData() {
        ActivityType = getIntent().getIntExtra(Constants.ACTIVITY_TYPE, 0);
        //  VehicleType = getIntent().getStringExtra("VehicleLengthActivity");
    }
}
