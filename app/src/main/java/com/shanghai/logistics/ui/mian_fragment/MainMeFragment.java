package com.shanghai.logistics.ui.mian_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.main_activity.main_me.CollectActivity;
import com.shanghai.logistics.ui.main_activity.main_me.DynamicActivity;
import com.shanghai.logistics.ui.main_activity.main_me.SettingActivity;
import com.shanghai.logistics.ui.main_activity.main_me.WalletActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 */
public class MainMeFragment extends SimpleFragment {

    @BindView(R.id.tv_head_title)
    TextView mTvTitle;
    @BindView(R.id.rl_personal_dynamic)
    RelativeLayout mRlPersonalDynamic;
    @BindView(R.id.rl_wallet)
    RelativeLayout mRlWallet;
    @BindView(R.id.rl_collect)
    RelativeLayout mRlCollect;
    @BindView(R.id.rl_setting)
    RelativeLayout mRlSetting;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_me;
    }

    @OnClick({R.id.rl_personal_dynamic, R.id.rl_wallet, R.id.rl_collect, R.id.rl_setting})
    void MainMeOncilck(View v) {
        switch (v.getId()) {
            case R.id.rl_personal_dynamic:
                startActivity(new Intent(getContext(), DynamicActivity.class));
                break;
            case R.id.rl_wallet:
                startActivity(new Intent(getContext(), WalletActivity.class));
                break;
            case R.id.rl_collect:
                startActivity(new Intent(getContext(), CollectActivity.class));
                break;
            case R.id.rl_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("我的");
    }

    @Override
    public void initImmersionBar() {

    }
}
