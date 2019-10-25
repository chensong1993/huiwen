package com.shanghai.logistics.ui.logistics_fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseFragment;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.base.connectors.logistics.LogisticsOrderInfoConnector;
import com.shanghai.logistics.models.entity.logistics.LOrderEntity;
import com.shanghai.logistics.models.entity.main.NewFriendEntity;
import com.shanghai.logistics.presenters.logistics.LogisticsOrderInfoPresenter;
import com.shanghai.logistics.ui.logistics_activity.AddDriverListActivity;
import com.shanghai.logistics.ui.logistics_activity.AddOrderListActivity;
import com.shanghai.logistics.ui.logistics_activity.BillingActivity;
import com.shanghai.logistics.ui.logistics_activity.NewOrderDetailActivity;
import com.shanghai.logistics.ui.logistics_adapter.LOrderListAdapter;
import com.shanghai.logistics.ui.user_activity.home_detail.PlaceAnOrderActivity;
import com.shanghai.logistics.util.RvLineUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 物流 新订单
 */
public class LNewOrderFragment extends BaseFragment<LogisticsOrderInfoPresenter> implements LogisticsOrderInfoConnector.View {

    private static final String TAG = "LNewOrderFragment";
    @BindView(R.id.ll_add_order_team)
    LinearLayout mLlAddOrderTeam;
    @BindView(R.id.ll_add_driver_team)
    LinearLayout mLlAddDriverTeam;
    @BindView(R.id.rv_new_order)
    RecyclerView mRvNewOrder;
    LOrderListAdapter mLOrderListAdapter;
    List<LOrderEntity> mOrderEntityList;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lnew_order;
    }

    @Override
    public void LogisticsOrderInfo(List<LOrderEntity> entities) {
        mLOrderListAdapter.setNewData(entities);
    }

    @Override
    public void LogisticsOrderInfoErr(String s) {

        switch (s) {
            case "0":
             //   sToastUtils.show("暂无数据");
                break;
            case "-1":

                break;
        }
        Log.i(TAG, "LogisticsOrderInfoErr: " + s);
    }

    @OnClick({R.id.ll_add_order_team, R.id.ll_add_driver_team})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_add_order_team:
                startActivity(new Intent(getActivity(), AddOrderListActivity.class));
                break;
            case R.id.ll_add_driver_team:
                startActivity(new Intent(getActivity(), AddDriverListActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getLogisticsOrderInfo(mLoginPhone, 1, 1);
        mOrderEntityList = new ArrayList<>();
        mLOrderListAdapter = new LOrderListAdapter(mOrderEntityList);
        mRvNewOrder.setAdapter(mLOrderListAdapter);
        mRvNewOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        RvLineUtil.Line(mRvNewOrder, getActivity(), 1);
        mLOrderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), NewOrderDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constants.All_VALUE,mLOrderListAdapter.getData().get(position).getOrderNo());
                intent.putExtra(Constants.All_VALUE, bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getLogisticsOrderInfo(mLoginPhone, 1, 1);
        mOrderEntityList = new ArrayList<>();
        mLOrderListAdapter = new LOrderListAdapter(mOrderEntityList);
        mRvNewOrder.setAdapter(mLOrderListAdapter);
        mRvNewOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        RvLineUtil.Line(mRvNewOrder, getActivity(), 1);
        mLOrderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), NewOrderDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constants.All_VALUE,mLOrderListAdapter.getData().get(position).getOrderNo());
                intent.putExtra(Constants.All_VALUE, bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public void initImmersionBar() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }


}
