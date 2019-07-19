package com.shanghai.logistics.ui.user_activity.home_detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.base.connectors.user.UserLinePhoneConnector;
import com.shanghai.logistics.models.entity.user.LinePhoneEntity;
import com.shanghai.logistics.presenters.user.UserLinePhonePresenter;
import com.shanghai.logistics.ui.user_adapter.UserLinePhoneAdapter;
import com.shanghai.logistics.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ContactActivity extends BaseActivity<UserLinePhonePresenter> implements UserLinePhoneConnector.View {
    public static final String TAG = "ContactActivity";
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_line_phone)
    RecyclerView mRvLinePhone;
    UserLinePhoneAdapter mUserLinePhoneAdapter;
    List<LinePhoneEntity> mLinePhoneList;

    @Override
    protected int getLayout() {
        return R.layout.activity_contact;
    }

    @OnClick({R.id.img_back})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }

    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("客服");
        Bundle bundle = getIntent().getBundleExtra(Constants.All_VALUE);
        if (bundle != null) {
            String id = bundle.getString(Constants.DEDICATED_LINE_PHONE_ID);
            Log.i(TAG, "initEventAndData: "+id);
            mPresenter.getLinePhoneList(id);
        }
        mLinePhoneList = new ArrayList<>();
        mUserLinePhoneAdapter = new UserLinePhoneAdapter(mLinePhoneList);
        mRvLinePhone.setLayoutManager(new LinearLayoutManager(this));
        mRvLinePhone.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 1, ContextCompat.getColor(this, R.color.hui)));
        mRvLinePhone.setAdapter(mUserLinePhoneAdapter);
        mUserLinePhoneAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String phone = mUserLinePhoneAdapter.getData().get(position).getPhone();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phone);
                intent.setData(data);
                startActivity(intent);
                Log.i(TAG, "onItemClick: "+phone);
            }
        });
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void UserLinePhoneList(List<LinePhoneEntity> entities) {
        mUserLinePhoneAdapter.setNewData(entities);
    }

    @Override
    public void UserLinePhoneLisErr(String s) {
        Log.i(TAG, "UserLinePhoneLisErr: " + s);
    }
}
