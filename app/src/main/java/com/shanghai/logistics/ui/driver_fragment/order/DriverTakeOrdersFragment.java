package com.shanghai.logistics.ui.driver_fragment.order;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.user_activity.order.OrderDetailActivity;
import com.shanghai.logistics.util.DataUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */
public class DriverTakeOrdersFragment extends SimpleFragment {

    @BindView(R.id.ll_orders)
    LinearLayout mLlOrders;
    @BindView(R.id.ll_select_date)
    LinearLayout mLlSelectDate;
    @BindView(R.id.tv_select_starting_date)
    TextView mTvSelectStarDate;
    @BindView(R.id.tv_select_destination_date)
    TextView mTvSelectdestinationDate;
    TimePickerView pvTime;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_driver_take_orders;
    }

    @OnClick({R.id.tv_select_starting_date, R.id.tv_select_destination_date,R.id.ll_orders})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_select_starting_date:
                pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTvSelectStarDate.setText(DataUtil.DateToString(date, DataUtil.DATE_TO_STRING_SHORT_PATTERN));
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.tv_select_destination_date:
                pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTvSelectdestinationDate.setText(DataUtil.DateToString(date, DataUtil.DATE_TO_STRING_SHORT_PATTERN));
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.ll_orders:
                startActivity(new Intent(getContext(), OrderDetailActivity.class));
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
