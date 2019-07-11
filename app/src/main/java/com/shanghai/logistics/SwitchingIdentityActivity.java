package com.shanghai.logistics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.util.ActivityCollector;
import com.shanghai.logistics.util.DestroyActivityUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SwitchingIdentityActivity extends SimpleActivity {

    @BindView(R.id.ll_user)
    LinearLayout mLlUser;
    @BindView(R.id.ll_driver)
    LinearLayout mLldriver;
    @BindView(R.id.ll_logistics)
    LinearLayout mLlLogistics;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_switching_identity;
    }

    @OnClick({R.id.img_back, R.id.ll_user, R.id.ll_driver, R.id.ll_logistics})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.ll_user:
                //判断指定activity是否已经存在
                if (ActivityCollector.isActivityExist(UserActivity.class)) {
                    finish();
                } else {
                    startActivity(new Intent(this, UserActivity.class));
                    finish();
                }
                //销毁指定activity
                DestroyActivityUtil.destoryActivity("DriverActivity");
                DestroyActivityUtil.destoryActivity("LogisticsActivity");
                break;
            case R.id.ll_driver:
                //判断指定activity是否已经存在
                if (ActivityCollector.isActivityExist(DriverActivity.class)) {
                    finish();
                } else {
                    startActivity(new Intent(this, DriverActivity.class));
                    finish();
                }
                DestroyActivityUtil.destoryActivity("UserActivity");
                DestroyActivityUtil.destoryActivity("LogisticsActivity");
                break;
            case R.id.ll_logistics:
                //判断指定activity是否已经存在
                if (ActivityCollector.isActivityExist(LogisticsActivity.class)) {
                    finish();
                } else {
                    startActivity(new Intent(this, LogisticsActivity.class));
                    finish();
                }
                DestroyActivityUtil.destoryActivity("UserActivity");
                DestroyActivityUtil.destoryActivity("DriverActivity");
                break;
        }

    }

    @Override
    protected void initEventAndData() {
      //  mLlLogistics.setVisibility(View.GONE);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.blue)
                .init();
        mTvTitle.setText("切换用户");
    }
}
