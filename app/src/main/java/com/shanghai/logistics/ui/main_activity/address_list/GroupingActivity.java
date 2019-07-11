package com.shanghai.logistics.ui.main_activity.address_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 联系人列表
 * */
public class GroupingActivity extends SimpleActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;

    @Override
    protected int getLayout() {
        return R.layout.activity_grouping;
    }

    @OnClick({R.id.img_back})
    void GroupingOnclick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("联系人");
    }
}
