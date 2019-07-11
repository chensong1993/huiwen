package com.shanghai.logistics.ui.logistics_fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.user_activity.home_detail.VehicleLengthActivity;
import com.shanghai.logistics.util.DataUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 找货
 */
public class FindGoodsFragment extends SimpleFragment {

    @BindView(R.id.tv_starting_point)
    TextView mTvStartingPoint;
    @BindView(R.id.tv_destination)
    TextView mTvDestination;
    @BindView(R.id.tv_chr_type)
    TextView mTvChrType;
    TimePickerView pvTime;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find_goods2;
    }

    @OnClick({ R.id.tv_starting_point, R.id.tv_destination, R.id.tv_chr_type})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right_content:
                break;
            case R.id.tv_starting_point:
                pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTvStartingPoint.setText(DataUtil.DateToString(date, DataUtil.DATE_TO_STRING_SHORT_PATTERN));
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.tv_destination:
                pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTvDestination.setText(DataUtil.DateToString(date, DataUtil.DATE_TO_STRING_SHORT_PATTERN));
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.tv_chr_type:
                Intent intent = new Intent(getActivity(), VehicleLengthActivity.class);
                intent.putExtra(Constants.ACTIVITY_TYPE, Constants.FIND_GOODS_FRAGMENT2);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void initEventAndData() {
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                mTvChrType.setText(data.getStringExtra(Constants.VEHICLE_TYPE));
                Log.i("onActivityResult", "onActivityResult: " + data.getStringExtra("vehicleLength"));
                break;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initImmersionBar() {

    }
}
