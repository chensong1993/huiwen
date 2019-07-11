package com.shanghai.logistics.ui.user_activity.home_detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

/*
* 车型
* */
public class VehicleTypeActivity extends SimpleActivity {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.ll_vehicle)
    LinearLayout mLlVehicle;

    @Override
    protected int getLayout() {
        return R.layout.activity_vehicle_type;
    }

    @OnClick({R.id.img_back, R.id.ll_vehicle})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.ll_vehicle:
                Intent intent=new Intent(this,VehicleLengthActivity.class);
                intent.putExtra("VehicleLengthActivity","小货车");
                startActivity(intent);
                finish();
                break;
        }

    }

    @Override
    protected void initEventAndData() {

    }
}
