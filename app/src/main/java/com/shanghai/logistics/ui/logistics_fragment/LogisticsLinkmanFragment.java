package com.shanghai.logistics.ui.logistics_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleFragment;

/**
 * 联系人
 */
public class LogisticsLinkmanFragment extends SimpleFragment {


    public LogisticsLinkmanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logistics_linkman, container, false);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void initImmersionBar() {

    }
}
