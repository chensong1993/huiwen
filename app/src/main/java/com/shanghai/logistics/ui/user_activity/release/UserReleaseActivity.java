package com.shanghai.logistics.ui.user_activity.release;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.widget.BasePopup.MenuPopup;

import butterknife.BindView;
import butterknife.OnClick;

public class UserReleaseActivity extends SimpleActivity {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ll_release_detail)
    LinearLayout mLlReleaseDetail;
    @BindView(R.id.img_quick)
    ImageView mImgQuick;

    @Override
    protected int getLayout() {
        return R.layout.fragment_release_user;
    }

    @OnClick({R.id.ll_release_detail, R.id.img_quick,R.id.img_back})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_release_detail:
                startActivity(new Intent(this, ReleaseDetailActivity.class));
                break;
            case R.id.img_quick:
                MenuPopup menuPopup = new MenuPopup(this);
                menuPopup.setBackgroundColor(R.color.black_10);
                menuPopup.showPopupWindow(mImgQuick);
                break;
            case R.id.img_back:
                finish();
                break;

        }

    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("发布中");
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.bottom_silent,R.anim.bottom_out);
    }
}
