package com.shanghai.logistics.ui.logistics_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.main_activity.main_me.PayActivity;
import com.shanghai.logistics.widget.BasePopup.PromotionPricePopup;

import butterknife.BindView;
import butterknife.OnClick;

/*
 *
 * 推广
 * */
public class PromoteActivity extends SimpleActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_promote)
    TextView mTvPromote;
    private static final String TAG = "PromoteActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_recommended;
    }

    @OnClick({R.id.img_back, R.id.tv_recharge, R.id.tv_promote})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_recharge:
                startActivity(new Intent(this, PayActivity.class));
                break;
            case R.id.img_back:
                Log.i(TAG, "onClick: ");
                finish();
                break;
            case R.id.tv_promote:
                PromotionPricePopup pricePopup = new PromotionPricePopup(this);
                pricePopup.setItemClickListener(new PromotionPricePopup.ItemClickListener() {
                    @Override
                    public void onItemClick(View v) {
                        switch (v.getId()) {
                            case R.id.tv_money1:
                                pricePopup.mEditText.setText("50");
                                break;
                            case R.id.tv_money2:
                                break;
                            case R.id.tv_money3:
                                break;
                            case R.id.tv_money4:
                                break;
                            case R.id.tv_money5:
                                break;
                            case R.id.tv_money6:
                                break;
                            case R.id.tv_cancel:
                                pricePopup.dismiss();
                                break;
                            case R.id.tv_confirm:
                                pricePopup.dismiss();
                                break;
                        }
                    }
                });
                pricePopup.showPopupWindow();
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("推荐");
    }
}
