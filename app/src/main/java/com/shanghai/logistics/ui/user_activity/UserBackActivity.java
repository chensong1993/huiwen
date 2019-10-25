package com.shanghai.logistics.ui.user_activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.base.connectors.user.UserBrandLineConnector;
import com.shanghai.logistics.base.connectors.user.UserBrandLogisticsConnector;
import com.shanghai.logistics.models.entity.user.BrandLineEntity;
import com.shanghai.logistics.presenters.user.UserBrandLinePresenter;
import com.shanghai.logistics.presenters.user.UserBrandLogisticsPresenter;
import com.shanghai.logistics.ui.user_adapter.UserBrandLineAdapter;
import com.shanghai.logistics.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserBackActivity extends BaseActivity<UserBrandLogisticsPresenter> implements UserBrandLogisticsConnector.View {
    public static final String TAG = "UserBackActivity";
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.rv_brandLine)
    RecyclerView mRvBrandLine;
    @BindView(R.id.et_brand)
    EditText mEtBrand;
    UserBrandLineAdapter mUserBrandLineAdapter;
    List<BrandLineEntity> mBrandLineList;
    String Address;

    @Override
    protected int getLayout() {
        return R.layout.activity_user_back;
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("今日推荐");
        if (App.kv.decodeString(Constants.ADDRESS) == null) {
            Address = "上海";
        } else {
            Address = App.kv.decodeString(Constants.ADDRESS);
        }
        mPresenter.getBrandLogistics(Address, 1);
        mBrandLineList = new ArrayList<>();
        mUserBrandLineAdapter = new UserBrandLineAdapter(mBrandLineList);
        mRvBrandLine.setLayoutManager(new GridLayoutManager(this, 2));
        mRvBrandLine.setAdapter(mUserBrandLineAdapter);
        // mRvBrandLine.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 1, ContextCompat.getColor(this, R.color.hui)));
        mUserBrandLineAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(UserBackActivity.this, ShopDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.STORE_ID, mUserBrandLineAdapter.getData().get(position).getId());
                bundle.putInt(Constants.ACTIVITY_TYPE, Constants.USER_BACK_ACTIVITY);
                intent.putExtra(Constants.ACTIVITY_TYPE, bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void UserBrandLogistics(List<BrandLineEntity> entities) {
        mUserBrandLineAdapter.setNewData(entities);
        Log.i(TAG, "UserBrandLine: " + entities.toString());
    }

    @Override
    public void UserBrandLogisticsErr(String s) {
        Log.i(TAG, "UserBrandLineErr: " + s);
        switch (s) {
            case "0":
                ToastUtils.show("暂无数据");
                break;

        }
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
