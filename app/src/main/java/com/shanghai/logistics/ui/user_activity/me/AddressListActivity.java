package com.shanghai.logistics.ui.user_activity.me;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.base.connectors.user.UserAddressConnector;
import com.shanghai.logistics.models.entity.user.AddressListEntity;
import com.shanghai.logistics.presenters.user.UserAddressPresenter;
import com.shanghai.logistics.ui.user_activity.home_detail.AddressActivity;
import com.shanghai.logistics.ui.user_adapter.UserAddressListAdapter;
import com.shanghai.logistics.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressListActivity extends BaseActivity<UserAddressPresenter> implements UserAddressConnector.View {
    private static final String TAG = "AddressListActivity";
    @BindView(R.id.tv_right_content)
    TextView mTvRightText;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.rv_address_list)
    RecyclerView mRvAddressList;
    List<AddressListEntity> mAddressList;
    UserAddressListAdapter mUserAddressAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_address_list;
    }

    @OnClick({R.id.img_back, R.id.tv_right_content})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right_content:
                startActivity(new Intent(this, AddAddressActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getUserAddress(mLoginPhone);
        mTvRightText.setVisibility(View.VISIBLE);
        mTvRightText.setText("添加地址");
        mAddressList = new ArrayList<>();
        mUserAddressAdapter = new UserAddressListAdapter(mAddressList);
        mRvAddressList.setAdapter(mUserAddressAdapter);
        mRvAddressList.setLayoutManager(new LinearLayoutManager(this));
        mRvAddressList.addItemDecoration(new RecycleViewDivider(this,LinearLayout.HORIZONTAL, 1, ContextCompat.getColor(this, R.color.hui)));
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void UserAddress(List<AddressListEntity> entities) {
        mUserAddressAdapter.setNewData(entities);
    }

    @Override
    public void UserAddressErr(String s) {
        Log.i(TAG, "UserAddressErr: " + s);
    }
}
