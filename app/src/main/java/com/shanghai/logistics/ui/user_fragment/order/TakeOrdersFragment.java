package com.shanghai.logistics.ui.user_fragment.order;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.user_activity.order.OrderDetailActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeOrdersFragment extends SimpleFragment {

    @BindView(R.id.ll_orders)
    LinearLayout mLlOrders;
    @BindView(R.id.ll_select_date)
    LinearLayout mLlSelectDate;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_take_orders;
    }

    @OnClick({R.id.ll_orders})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_orders:
                startActivity(new Intent(getContext(), OrderDetailActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mLlSelectDate.setVisibility(View.VISIBLE);
    }

    @Override
    public void initImmersionBar() {

    }
}
