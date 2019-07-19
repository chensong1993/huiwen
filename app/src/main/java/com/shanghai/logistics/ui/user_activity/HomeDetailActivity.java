package com.shanghai.logistics.ui.user_activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.connectors.user.UserSpecialConnector;
import com.shanghai.logistics.models.entity.user.SpecialEntity;
import com.shanghai.logistics.presenters.user.UserSpecialPresenter;
import com.shanghai.logistics.ui.logistics_activity.AddShopActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.ContactActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.PlaceAnOrderActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.ServiceActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeDetailActivity extends BaseActivity<UserSpecialPresenter> implements UserSpecialConnector.View {
    public static final String TAG="HomeDetailActivity";
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
    @BindView(R.id.tv_special_name)
    TextView mTvSpecialName;
    @BindView(R.id.tv_form_where)
    TextView mTvFormWhere;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.tv_wight)
    TextView mTvWight;
    @BindView(R.id.tv_volume)
    TextView mTvVolume;
    @BindView(R.id.tv_square)
    TextView mTvSquare;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_introduction)
    TextView mTvIntroduction;
    @BindView(R.id.img_head)
    ImageView mImgHead;
    Intent intent;
    Bundle bundle;
    int type;
    int storeId,dedicatedLine;
    String dedicatedLinePhoneId;
    String storeAccount;
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
                intent=new Intent(this, MoreCommentsActivity.class);
                bundle=new Bundle();
                bundle.putInt(Constants.STORE_ID,storeId);
                intent.putExtra(Constants.All_VALUE,bundle);
                startActivity(intent);
                break;
            case R.id.ll_shop:
                // startActivity(new Intent(this,));
                break;
            case R.id.ll_linkman:
                intent=new Intent(this, ContactActivity.class);
                bundle=new Bundle();
                bundle.putString(Constants.DEDICATED_LINE_PHONE_ID,dedicatedLinePhoneId);
                Log.i(TAG, "onClick: "+dedicatedLinePhoneId);
                intent.putExtra(Constants.All_VALUE,bundle);
                startActivity(intent);
                break;
            case R.id.ll_service:
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case R.id.ll_place_an_order:
                Intent intent=new Intent(this, PlaceAnOrderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt(Constants.ACTIVITY_TYPE,Constants.HOME_DETAIL_ACTIVITY);
                bundle.putInt(Constants.STORE_ID,storeId);
                bundle.putInt(Constants.DEDICATED_LINE_PHONE_ID,dedicatedLine);
                bundle.putString(Constants.STORE_ACCOUNT,storeAccount);
                intent.putExtra(Constants.ACTIVITY_TYPE,bundle);
                startActivity(intent);
                break;
            case R.id.tv_shop_manage:
                Intent intent1=new Intent(this, AddShopActivity.class);
                intent1.putExtra(Constants.STORE_ID,storeId);
                startActivity(intent1);
                break;
        }

    }

    @Override
    protected void initEventAndData() {

        Bundle bundle = getIntent().getBundleExtra(Constants.ACTIVITY_TYPE);
        if (bundle != null) {
             type = bundle.getInt(Constants.ACTIVITY_TYPE);
             storeId = bundle.getInt(Constants.STORE_ID);
            
            switch (type) {
                case Constants.LOGISTICS_ME_FRAGMENT:
                    mPresenter.getUserSpecial(storeId);
                    mTvShopManage.setVisibility(View.VISIBLE);
                    mLlBottom.setVisibility(View.GONE);
                    break;
                case Constants.USER_HOME_FRAGMENT:
                    mPresenter.getUserSpecial(storeId);
                    Log.i(TAG, "initEventAndData: "+storeId);
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

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void UserSpecial(SpecialEntity e) {
        Log.i("UserSpecial", "UserSpecial: "+e.toString());
        //顶部标题
        Glide.with(this).load(Constants.MAIN_URL+e.getMainImg()).into(mImgHead);
        //专线名称
        mTvSpecialName.setText(e.getLineName());
        //发货地和收货地
        mTvFormWhere.setText(e.getEndCity());
        //所在地
        mTvCity.setText(e.getServiceAddress());
        //每吨价格
        mTvWight.setText(e.getWeight()+"/吨");
        //每方价格
        mTvVolume.setText(e.getVolume()+"方/吨");
        //月销
        mTvSquare.setText("月销"+e.getTotalSales()+"笔");
        //时限
        mTvTime.setText(e.getAging()+"小时直达");
        //商品介绍
        mTvIntroduction.setText(e.getIntroduction());

        dedicatedLinePhoneId=e.getDedicatedLinePhoneId();

        storeAccount=e.getPhone();

        dedicatedLine=e.getId();
        Log.i(TAG, dedicatedLinePhoneId+"UserSpecial: "+e.getDedicatedLinePhoneId()+e.getIntroduction());
    }

    @Override
    public void UserSpecialErr(String s) {
        Log.i(TAG, "UserShopDetailErr: ");
    }
}
