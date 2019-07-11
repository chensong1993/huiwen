package com.shanghai.logistics.ui.user_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.user_activity.release.ReleaseDetailActivity;
import com.shanghai.logistics.widget.BasePopup.MenuPopup;

import butterknife.BindView;
import butterknife.OnClick;
import razerdp.basepopup.QuickPopupBuilder;
import razerdp.basepopup.QuickPopupConfig;

/**
 * 发布
 */
public class UserReleaseFragment extends SimpleFragment {
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ll_release_detail)
    LinearLayout mLlReleaseDetail;
    @BindView(R.id.img_quick)
    ImageView mImgQuick;

    @Override

    protected int getLayoutId() {
        return R.layout.fragment_release_user;
    }

    @OnClick({R.id.ll_release_detail, R.id.img_quick})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_release_detail:
                startActivity(new Intent(getActivity(), ReleaseDetailActivity.class));
                break;
            case R.id.img_quick:
                MenuPopup menuPopup = new MenuPopup(getActivity());
                menuPopup.setBackgroundColor(R.color.black_10);
                menuPopup.showPopupWindow(mImgQuick);
                break;
            case R.id.img_back:
                getActivity().finish();
                break;

        }

    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("发布中");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // App.refWatcher.watch(this);
    }

//    @Override
//    protected void initInject() {
//        getFragmentComponent().inject(this);
//    }


    @Override
    public void initImmersionBar() {

    }
}
