package com.shanghai.logistics.ui.driver_fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.driver_activity.AddLineActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的路线
 */
public class MyLineFragment extends SimpleFragment {

    @BindView(R.id.tv_add_line)
    TextView mTvAddLine;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_line;
    }

    @OnClick({R.id.tv_add_line})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_line:
                startActivity(new Intent(getActivity(), AddLineActivity.class));
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
