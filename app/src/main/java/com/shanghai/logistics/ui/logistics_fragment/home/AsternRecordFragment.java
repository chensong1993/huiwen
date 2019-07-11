package com.shanghai.logistics.ui.logistics_fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.logistics_activity.LogisticsDetailsActivity;

import butterknife.OnClick;

/**
 * 到车记录
 */
public class AsternRecordFragment extends SimpleFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_astern_record;
    }

    @OnClick({R.id.ll_orders})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_orders:
                startActivity(new Intent(getContext(), LogisticsDetailsActivity.class));
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
