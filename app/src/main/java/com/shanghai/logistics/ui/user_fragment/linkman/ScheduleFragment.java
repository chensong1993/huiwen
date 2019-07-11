package com.shanghai.logistics.ui.user_fragment.linkman;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shanghai.logistics.R;
import com.shanghai.logistics.SwitchingIdentityActivity;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 行程单
 */
public class ScheduleFragment extends SimpleFragment {

    @BindView(R.id.ll_QR)
    LinearLayout mLlQR;
    @BindView(R.id.ll_switch_identities)
    LinearLayout mLlSwitchIdentities;
    @BindView(R.id.ll_location)
    LinearLayout mLlLocation;
    @BindView(R.id.ll_QR_and_identities)
    LinearLayout mLlQRAndIdentities;
    int activityType;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_schedule;
    }

    @OnClick({R.id.ll_QR, R.id.ll_switch_identities})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_QR:

                break;
            case R.id.ll_switch_identities:
                startActivity(new Intent(getActivity(), SwitchingIdentityActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            activityType = bundle.getInt(Constants.ACTIVITY_TYPE, -1);
            switch (activityType) {
                case Constants.DRIVER_ME_FRAGMENT:
                    mLlLocation.setVisibility(View.GONE);
                    mLlQRAndIdentities.setVisibility(View.VISIBLE);
                    break;
                default:
                    mLlLocation.setVisibility(View.VISIBLE);
                    mLlQRAndIdentities.setVisibility(View.GONE);
                    break;
            }
        }


    }

    @Override
    public void initImmersionBar() {

    }
}
