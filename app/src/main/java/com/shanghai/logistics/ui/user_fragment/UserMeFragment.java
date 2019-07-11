package com.shanghai.logistics.ui.user_fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.SwitchingIdentityActivity;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.user_activity.me.AddressListActivity;
import com.shanghai.logistics.ui.user_activity.me.ApproveActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 */
public class UserMeFragment extends SimpleFragment {

    @BindView(R.id.tv_right_content)
    TextView mTvRightText;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.ll_address_list)
    LinearLayout mLlAddressList;
//    @BindView(R.id.ll_switching_identity)
//    LinearLayout mLlSwitchingIdentity;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me_user;
    }

    @OnClick({R.id.img_back, R.id.tv_right_content, R.id.ll_address_list,R.id.ll_switching_identity})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.tv_right_content:
                startActivity(new Intent(getContext(), ApproveActivity.class));
                break;
            case R.id.ll_address_list:
                startActivity(new Intent(getContext(), AddressListActivity.class));
                break;
            case R.id.ll_switching_identity:
                startActivity(new Intent(getContext(), SwitchingIdentityActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvRightText.setVisibility(View.VISIBLE);
        mTvRightText.setText("认证");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void initImmersionBar() {
//        ImmersionBar.with(this).statusBarColorTransformEnable(false)
//                .keyboardEnable(false)
//                .statusBarView(mTvStatusBar)
//                .statusBarColor(R.color.black)
//                .init();
    }
}
