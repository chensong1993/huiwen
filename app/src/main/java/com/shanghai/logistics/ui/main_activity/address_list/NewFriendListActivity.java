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

public class NewFriendListActivity extends SimpleActivity {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.img_empty)
    ImageView mImgEmpty;
    @BindView(R.id.tv_accept)
    TextView mTvAccept;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_new_friend_list;
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("新朋友");
    }

    @OnClick({R.id.img_back, R.id.img_empty, R.id.tv_accept})
    void newFriendOnclick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_empty:
                break;
            case R.id.tv_accept:
                break;
        }
    }


}
