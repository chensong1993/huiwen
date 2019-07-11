package com.shanghai.logistics.ui.driver_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.driver_activity.ScheduleDetailActivity;
import com.shanghai.logistics.ui.driver_activity.ScheduleReleaseActivity;
import com.shanghai.logistics.ui.user_activity.release.ReleaseDetailActivity;
import com.shanghai.logistics.widget.BasePopup.MenuPopup;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 司机发布
 */
public class DriverReleaseFragment extends SimpleFragment {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ll_release_detail)
    LinearLayout mLlReleaseDetail;
    @BindView(R.id.tv_right_content)
    TextView mTvRightTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_driver_release;
    }

    @OnClick({R.id.ll_release_detail, R.id.img_back, R.id.tv_right_content})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_release_detail:
                startActivity(new Intent(getActivity(), ScheduleDetailActivity.class));
                break;
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.tv_right_content:
                startActivity(new Intent(getActivity(), ScheduleReleaseActivity.class));
                break;

        }

    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("发布中");
        mTvRightTitle.setVisibility(View.VISIBLE);
        mTvRightTitle.setText("发布");
    }

    @Override
    public void initImmersionBar() {

    }
}
