
package com.shanghai.logistics.ui.main_activity.main_me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.activity.login.LoginActivity;
import com.shanghai.logistics.util.DestroyActivityUtil;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 设置
 * */
public class SettingActivity extends SimpleActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @OnClick({R.id.img_back, R.id.tv_login_out})
    void CollectOnclick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_login_out:
                App.kv.remove(Constants.PHONE);
                App.kv.remove(Constants.USER_PHONE);
                //销毁指定activity
                DestroyActivityUtil.destoryActivity("MainActivity");
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("设置");
    }
}
