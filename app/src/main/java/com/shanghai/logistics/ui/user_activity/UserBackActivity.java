package com.shanghai.logistics.ui.user_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class UserBackActivity extends SimpleActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.ll_shop)
    LinearLayout mLlShop;

    @Override
    protected int getLayout() {
        return R.layout.activity_user_back;
    }

    @OnClick({R.id.img_back, R.id.ll_shop})
    void CollectOnclick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.ll_shop:
                Intent intent = new Intent(this, ShopDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.ACTIVITY_TYPE, Constants.USER_BACK_ACTIVITY);
                intent.putExtra(Constants.ACTIVITY_TYPE, bundle);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("今日推荐");
    }
}
