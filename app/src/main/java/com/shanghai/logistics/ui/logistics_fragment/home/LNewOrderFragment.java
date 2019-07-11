package com.shanghai.logistics.ui.logistics_fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.logistics_activity.AddDriverListActivity;
import com.shanghai.logistics.ui.logistics_activity.AddOrderListActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 物流 新订单
 */
public class LNewOrderFragment extends SimpleFragment {


    @BindView(R.id.ll_add_order_team)
    LinearLayout mLlAddOrderTeam;
    @BindView(R.id.ll_add_driver_team)
    LinearLayout mLlAddDriverTeam;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lnew_order;
    }

    @OnClick({R.id.ll_add_order_team, R.id.ll_add_driver_team})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_add_order_team:
                startActivity(new Intent(getActivity(),AddOrderListActivity.class));
                break;
            case R.id.ll_add_driver_team:
                startActivity(new Intent(getActivity(),AddDriverListActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void initImmersionBar() {

    }
}
