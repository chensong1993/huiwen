package com.shanghai.logistics.ui.driver_activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.user_activity.release.ReleaseDetailActivity;
import com.shanghai.logistics.widget.BasePopup.MenuPopup;

import butterknife.BindView;
import butterknife.OnClick;

public class DriverReleaseActivity extends SimpleActivity {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ll_release_detail)
    LinearLayout mLlReleaseDetail;
    @BindView(R.id.tv_right_content)
    TextView mTvRightTitle;

    @Override
    protected int getLayout() {
        return R.layout.fragment_driver_release;
    }

    @OnClick({R.id.ll_release_detail, R.id.img_back, R.id.tv_right_content})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_release_detail:
                startActivity(new Intent(this, ScheduleDetailActivity.class));
                break;
            case R.id.img_back:
               finish();
                break;
            case R.id.tv_right_content:
                startActivity(new Intent(this, ScheduleReleaseActivity.class));
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
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.bottom_silent,R.anim.bottom_out);
    }
}
