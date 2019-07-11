package com.shanghai.logistics.ui.logistics_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 添加接单列表
 * */
public class AddOrderListActivity extends SimpleActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_right_content)
    TextView mTvRightTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_order_list;
    }

    @OnClick({R.id.img_back, R.id.tv_right_content})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right_content:
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvRightTitle.setVisibility(View.VISIBLE);
        mTvRightTitle.setText("添加");
    }
}
