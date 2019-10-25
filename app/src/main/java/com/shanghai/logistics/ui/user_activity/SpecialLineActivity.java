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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
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
    String Address;

    @Override
    protected int getLayout() {
        return R.layout.activity_special_line;
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("今日推荐");
        if (App.kv.decodeString(Constants.ADDRESS) == null) {
            Address = "上海";
        } else {
            Address = App.kv.decodeString(Constants.ADDRESS);
        }

        mPresenter.getBrandLine(Address, 1);
        mBrandLineList = new ArrayList<>();
        mUserHomeListAdapter = new UserHomeListAdapter(mBrandLineList);
        mRvBrandLine.setLayoutManager(new GridLayoutManager(this, 2));
        mRvBrandLine.setAdapter(mUserHomeListAdapter);
        mUserHomeListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(SpecialLineActivity.this, HomeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.STORE_ID, mUserHomeListAdapter.getData().get(position).getId());
                bundle.putInt(Constants.ACTIVITY_TYPE, Constants.USER_HOME_FRAGMENT);
                intent.putExtra(Constants.ACTIVITY_TYPE, bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void UserBrandLine(List<HomeListEntity> entities) {
        mUserHomeListAdapter.setNewData(entities);
    }

    @Override
    public void UserBrandLineErr(String s) {
        switch (s) {
            case "0":
                ToastUtils.show("暂无数据");
                break;

        }
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
