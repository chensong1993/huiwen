package com.shanghai.logistics.ui.main_activity.address_list;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.yzq.zxinglibrary.common.Constant;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalInforActivity extends SimpleActivity {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.img_head)
    ImageView mImgHead;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_nick_name)
    TextView mTvNickName;
    @BindView(R.id.tv_call)
    TextView mTvCall;
    String mPhone, mHeadUrl, mNickName;

    @Override
    protected int getLayout() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void initEventAndData() {
        Bundle bundle = getIntent().getBundleExtra(Constants.All_VALUE);
        if (bundle != null) {
            mPhone = bundle.getString(Constants.PHONE);
            mHeadUrl = bundle.getString(Constants.HEAD_URL);
            mNickName = bundle.getString(Constants.NICK_NAME);
            mTvName.setText(mNickName);
            mTvNickName.setText(mNickName);
            Glide.with(this).load(mHeadUrl).into(mImgHead);
        }
    }

    @SuppressLint("MissingPermission")
    @OnClick({R.id.img_back, R.id.img_head, R.id.tv_call})
    void POnclick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_head:

                break;
            case R.id.tv_call:
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:" + mPhone);
                intent.setData(uri);
                startActivity(intent);
                break;
        }
    }
}
