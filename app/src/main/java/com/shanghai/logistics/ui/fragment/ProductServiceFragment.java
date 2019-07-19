package com.shanghai.logistics.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.models.entity.user.UserShopEntity;
import com.shanghai.logistics.ui.logistics_adapter.LShopListAdapter;
import com.shanghai.logistics.ui.user_activity.HomeDetailActivity;
import com.shanghai.logistics.ui.user_adapter.UserHomeListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 产品服务
 */
public class ProductServiceFragment extends SimpleFragment {
    private static final String TAG = "ProductServiceFragment";
    Bundle bundle;
    @BindView(R.id.rv_shop_list)
    RecyclerView mRvShopList;
    LShopListAdapter mLShopListAdapter;
    UserShopEntity userShopEntity;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product;
    }

    @OnClick({})
    void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    protected void initEventAndData() {
        bundle = getArguments();
        if (bundle != null) {
            userShopEntity = (UserShopEntity) bundle.getSerializable(Constants.STORE_LIST);
            Log.i(TAG, "initEventAndData: " + bundle.getSerializable(Constants.STORE_LIST));
            mLShopListAdapter = new LShopListAdapter(userShopEntity.getDedicatedLine());
            mRvShopList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            mRvShopList.setAdapter(mLShopListAdapter);
            mLShopListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(getActivity(), HomeDetailActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt(Constants.ACTIVITY_TYPE,Constants.LOGISTICS_ME_FRAGMENT);
                    bundle.putInt(Constants.STORE_ID,mLShopListAdapter.getData().get(position).getId());
                    intent.putExtra(Constants.ACTIVITY_TYPE, bundle);
                    startActivity(intent);
                }
            });

        }


    }

    @Override
    public void initImmersionBar() {

    }
}
