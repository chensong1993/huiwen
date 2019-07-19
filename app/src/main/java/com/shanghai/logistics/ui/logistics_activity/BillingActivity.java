package com.shanghai.logistics.ui.logistics_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.BaseFragment;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.base.connectors.logistics.LogisticsOrderInfoConnector;
import com.shanghai.logistics.models.entity.logistics.LOrderEntity;
import com.shanghai.logistics.presenters.logistics.LogisticsOrderInfoPresenter;
import com.shanghai.logistics.ui.logistics_adapter.LOrderListAdapter;
import com.shanghai.logistics.ui.user_activity.home_detail.PlaceAnOrderActivity;
import com.shanghai.logistics.util.RvLineUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 开单
 * */
public class BillingActivity extends BaseActivity<LogisticsOrderInfoPresenter> implements LogisticsOrderInfoConnector.View {
    private static final String TAG = "BillingActivity";
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.rv_billing)
    RecyclerView mRvBilling;
    LOrderListAdapter mLOrderListAdapter;
    List<LOrderEntity> mOrderEntityList;

    @Override
    protected int getLayout() {
        return R.layout.activity_billing;
    }


    @Override
    protected void initEventAndData() {
        mTvTitle.setText("待入库");
        mPresenter.getLogisticsOrderInfo("15169169195", 3, 1);
        mOrderEntityList = new ArrayList<>();
        mLOrderListAdapter = new LOrderListAdapter(mOrderEntityList);
        mRvBilling.setAdapter(mLOrderListAdapter);
        mRvBilling.setLayoutManager(new LinearLayoutManager(this));
        RvLineUtil.Line(mRvBilling, this, 1);
        mLOrderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(BillingActivity.this, PlaceAnOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.BILLING_VALUE,mLOrderListAdapter.getData().get(position));
                bundle.putInt(Constants.ACTIVITY_TYPE, Constants.BILLING_ACTIVITY);
                intent.putExtra(Constants.ACTIVITY_TYPE, bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getLogisticsOrderInfo("15169169195", 3, 1);
    }

    @Override
    public void LogisticsOrderInfo(List<LOrderEntity> entities) {
        mLOrderListAdapter.setNewData(entities);
        Log.i(TAG, "LogisticsOrderInfo: "+entities.size());
    }

    @Override
    public void LogisticsOrderInfoErr(String s) {
        Log.i(TAG, "LogisticsOrderInfoErr: ");
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
    protected void initInject() {
        getActivityComponent().inject(this);
    }


}
