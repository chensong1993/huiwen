package com.shanghai.logistics.ui.main_activity.main_me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;
/*
* 个人动态
* */
public class DynamicActivity extends SimpleActivity {

    @BindView(R.id.img_back)
    ImageView mImgBack;

    @Override
    protected int getLayout() {
        return R.layout.activity_dynamic;
    }

    @OnClick({R.id.img_back})
    void DynamicOnclick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    @Override
    protected void initEventAndData() {

    }
}
