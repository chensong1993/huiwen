package com.shanghai.logistics.ui.user_fragment;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.SwitchingIdentityActivity;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseFragment;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.base.connectors.user.UserInfoConnector;
import com.shanghai.logistics.models.entity.user.UserInfoEntity;
import com.shanghai.logistics.presenters.user.UserUserInfoPresenter;
import com.shanghai.logistics.ui.user_activity.me.AddressListActivity;
import com.shanghai.logistics.ui.user_activity.me.CertificationActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 */
public class UserMeFragment extends BaseFragment<UserUserInfoPresenter> implements UserInfoConnector.View {

    private static final String TAG = "UserMeFragment";
    @BindView(R.id.tv_right_content)
    TextView mTvRightText;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.ll_address_list)
    LinearLayout mLlAddressList;
    @BindView(R.id.img_head)
    ImageView mImgHead;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_user_service_point)
    TextView mTvUserServicePoint;
//    @BindView(R.id.ll_switching_identity)
//    LinearLayout mLlSwitchingIdentity;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me_user;
    }

    @OnClick({R.id.img_back, R.id.tv_right_content, R.id.ll_address_list, R.id.ll_switching_identity})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.tv_right_content:
                startActivity(new Intent(getContext(), CertificationActivity.class));
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
        mPresenter.getUserInfo("15169169195");
        mTvRightText.setVisibility(View.VISIBLE);
        mTvRightText.setText("认证");
    }

    @Override
    public void UserInfo(UserInfoEntity userInfo) {
        mTvName.setText(userInfo.getRealName());
        mTvUserServicePoint.setText(userInfo.getUserServicePoint() + "");
       Glide.with(getActivity()).load(Constants.MAIN_URL+userInfo.getHeadImgUrl()).into(mImgHead);
        Log.i(TAG, "UserInfo: "+userInfo.getHeadImgUrl());
    }

    @Override
    public void UserInfoErr(String s) {
        //ToastUtils.show("");
        Log.i(TAG, "UserInfoErr: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
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
