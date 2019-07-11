package com.shanghai.logistics.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.user_activity.HomeDetailActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 产品服务
 */
public class ProductServiceFragment extends SimpleFragment {

    @BindView(R.id.ll_business_line)
    LinearLayout mLlBusinessLine;
    Bundle bundle;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product;
    }

    @OnClick({R.id.ll_business_line})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_business_line:
                if (bundle != null) {
                    Intent intent = new Intent(getActivity(), HomeDetailActivity.class);
                    intent.putExtra(Constants.ACTIVITY_TYPE, bundle);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        bundle = getArguments();
    }

    @Override
    public void initImmersionBar() {

    }
}
