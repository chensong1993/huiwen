package com.shanghai.logistics.ui.user_activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.logistics_activity.AddShopActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.ContactActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.PlaceAnOrderActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.ServiceActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeDetailActivity extends SimpleActivity {

    @BindView(R.id.tv_more)
    TextView mTvMore;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.ll_shop)
    LinearLayout mLlShop;
    @BindView(R.id.ll_linkman)
    LinearLayout mLlLinkMan;
    @BindView(R.id.ll_service)
    LinearLayout mLlService;
    @BindView(R.id.ll_place_an_order)
    LinearLayout mLlPlaceAnOrder;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    @BindView(R.id.tv_shop_manage)
    TextView mTvShopManage;
    int type;

    @Override
    protected int getLayout() {
        return R.layout.activity_home_detail;
    }

    @OnClick({R.id.tv_shop_manage, R.id.img_back, R.id.tv_more, R.id.ll_shop, R.id.ll_linkman, R.id.ll_service, R.id.ll_place_an_order})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_more:
                startActivity(new Intent(this, MoreCommentsActivity.class));
                break;
            case R.id.ll_shop:
                // startActivity(new Intent(this,));
                break;
            case R.id.ll_linkman:
                startActivity(new Intent(this, ContactActivity.class));
                break;
            case R.id.ll_service:
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case R.id.ll_place_an_order:
                startActivity(new Intent(this, PlaceAnOrderActivity.class));
                break;
            case R.id.tv_shop_manage:
                startActivity(new Intent(this, AddShopActivity.class));
                break;
        }

    }

    @Override
    protected void initEventAndData() {
        Bundle bundle = getIntent().getBundleExtra(Constants.ACTIVITY_TYPE);
        if (bundle != null) {
            type = bundle.getInt(Constants.ACTIVITY_TYPE);
            switch (type) {
                case Constants.LOGISTICS_ME_FRAGMENT:
                    mTvShopManage.setVisibility(View.VISIBLE);
                    mLlBottom.setVisibility(View.GONE);
                    break;
                case Constants.USER_HOME_FRAGMENT:
                    mTvShopManage.setVisibility(View.GONE);
                    mLlBottom.setVisibility(View.VISIBLE);
                    break;
                case Constants.USER_BACK_ACTIVITY:
                    mTvShopManage.setVisibility(View.GONE);
                    mLlBottom.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
}
