package com.shanghai.logistics.ui.logistics_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamListActivity extends SimpleActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    private static final String TAG = "TeamListActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_team_list;
    }

    @OnClick({R.id.img_back, R.id.ll_team})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_team:
                startActivity(new Intent(this, OrderActivity.class));
                break;
            case R.id.img_back:
                Log.i(TAG, "onClick: ");
                finish();
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("团队");
    }
}
