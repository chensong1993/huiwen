package com.shanghai.logistics.ui.user_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.base.connectors.user.UserBrandLineConnector;
import com.shanghai.logistics.models.entity.user.HomeListEntity;
import com.shanghai.logistics.presenters.user.UserBrandLinePresenter;
import com.shanghai.logistics.ui.user_adapter.UserHomeListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SpecialLineActivity extends BaseActivity<UserBrandLinePresenter> implements UserBrandLineConnector.View {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_brand_line)
    RecyclerView mRvBrandLine;
    List<HomeListEntity> mBrandLineList;
    UserHomeListAdapter mUserHomeListAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_special_line;
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("今日推荐");
        mPresenter.getBrandLine("上海", 1);
        mBrandLineList = new ArrayList<>();
        mUserHomeListAdapter = new UserHomeListAdapter(mBrandLineList);
        mRvBrandLine.setLayoutManager(new GridLayoutManager(this, 2));
        mRvBrandLine.setAdapter(mUserHomeListAdapter);

    }

    @Override
    public void UserBrandLine(List<HomeListEntity> entities) {
        mUserHomeListAdapter.setNewData(entities);
    }

    @Override
    public void UserBrandLineErr(String s) {
      //  Log.i(TAG, "UserBrandLineErr: " + s);
    }

    @OnClick({R.id.img_back})
    void CollectOnclick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


}
