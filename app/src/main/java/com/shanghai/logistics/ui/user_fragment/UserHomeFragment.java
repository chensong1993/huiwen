package com.shanghai.logistics.ui.user_fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.main_activity.main_me.CollectActivity;
import com.shanghai.logistics.ui.main_activity.main_me.DynamicActivity;
import com.shanghai.logistics.ui.main_activity.main_me.SettingActivity;
import com.shanghai.logistics.ui.main_activity.main_me.WalletActivity;
import com.shanghai.logistics.ui.user_activity.HomeDetailActivity;
import com.shanghai.logistics.ui.user_activity.SpecialLineActivity;
import com.shanghai.logistics.ui.user_activity.UserBackActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class UserHomeFragment extends SimpleFragment {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_brand)
    TextView mTvBrand;
    @BindView(R.id.tv_special)
    TextView mTvSpecial;
    @BindView(R.id.rl_red_title)
    RelativeLayout mRlRedTitle;
    @BindView(R.id.ll_business_line)
    LinearLayout mLlBusinessLine;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_user;
    }


    @Override
    protected void initEventAndData() {
        mTvTitle.setText("首页");
        Bundle bundle = getArguments();
        if (bundle != null) {
            int type = bundle.getInt(Constants.ACTIVITY_TYPE);
            switch (type) {
                case Constants.USER_HOME_FRAGMENT:
                    mRlRedTitle.setVisibility(View.GONE);
                    break;
                default:
                    mRlRedTitle.setVisibility(View.VISIBLE);
                    break;
            }

        }
    }

    @OnClick({R.id.img_back, R.id.tv_brand, R.id.tv_special, R.id.ll_business_line})
    void UserOnclick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.tv_brand:
                startActivity(new Intent(getActivity(), UserBackActivity.class));
                break;
            case R.id.tv_special:
                startActivity(new Intent(getActivity(), SpecialLineActivity.class));
                break;
            case R.id.ll_business_line:
                Intent intent = new Intent(getActivity(), HomeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.ACTIVITY_TYPE, Constants.USER_HOME_FRAGMENT);
                intent.putExtra(Constants.ACTIVITY_TYPE, bundle);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void initImmersionBar() {
//        ImmersionBar.with(this)
//                .statusBarView(mTvStatusBar)
//                .statusBarColor(R.color.black)
//                .init();
    }
}
