package com.shanghai.logistics.ui.logistics_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shanghai.logistics.QRCodesActivity;
import com.shanghai.logistics.R;
import com.shanghai.logistics.SwitchingIdentityActivity;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseFragment;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.base.connectors.logistics.LogisticsUserInfoConnector;
import com.shanghai.logistics.models.entity.logistics.LUserInfoEntity;
import com.shanghai.logistics.presenters.logistics.LogisticsUserInfoPresenter;
import com.shanghai.logistics.ui.logistics_activity.AddStoresActivity;
import com.shanghai.logistics.ui.logistics_activity.CertificationActivity;
import com.shanghai.logistics.ui.logistics_activity.LogisticsDetailsActivity;
import com.shanghai.logistics.ui.logistics_activity.PromoteActivity;
import com.shanghai.logistics.ui.logistics_activity.TeamListActivity;
import com.shanghai.logistics.ui.logistics_adapter.LogisticsMyShopAdapter;
import com.shanghai.logistics.ui.user_activity.MoreCommentsActivity;
import com.shanghai.logistics.ui.user_activity.ShopDetailActivity;
import com.shanghai.logistics.ui.user_activity.me.AddressListActivity;
import com.shanghai.logistics.util.RvLineUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 */
public class LogisticsMeFragment extends BaseFragment<LogisticsUserInfoPresenter> implements LogisticsUserInfoConnector.View {
    private static final String TAG = "LogisticsMeFragment";
    @BindView(R.id.tv_right_content)
    TextView mTvRightText;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.img_head)
    ImageView mImgHead;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_companyName)
    TextView mTvCompanyName;
    @BindView(R.id.tv_storeServicePoint)
    TextView mTvStoreServicePoint;
    @BindView(R.id.ll_address_list)
    LinearLayout mLlAddressList;
    @BindView(R.id.ll_comment_manage)
    LinearLayout mLlCommentManage;
    @BindView(R.id.ll_team_manage)
    LinearLayout mLlTeamManage;
    @BindView(R.id.ll_promote)
    LinearLayout mLlPromote;
    @BindView(R.id.ll_add_shop)
    LinearLayout mLlAddShop;
    @BindView(R.id.rv_myShop)
    RecyclerView mRvMyShop;
    LogisticsMyShopAdapter myShopAdapter;
    List<LUserInfoEntity.Store> mUserInfoEntityList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_logistics_me;
    }

    @OnClick({R.id.tv_qr, R.id.ll_add_shop, R.id.ll_promote, R.id.ll_team_manage, R.id.ll_comment_manage, R.id.img_back, R.id.tv_right_content, R.id.ll_address_list, R.id.ll_switching_identity})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_qr: //二维码
                startActivity(new Intent(getActivity(), QRCodesActivity.class));
                break;
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.tv_right_content: // 认证
                startActivity(new Intent(getContext(), CertificationActivity.class));
                break;
            case R.id.ll_address_list: //地址薄
                startActivity(new Intent(getContext(), AddressListActivity.class));
                break;
            case R.id.ll_switching_identity: //切换身份
                startActivity(new Intent(getContext(), SwitchingIdentityActivity.class));
                break;
            case R.id.ll_comment_manage: //评论管理
                startActivity(new Intent(getActivity(), MoreCommentsActivity.class));
                break;
            case R.id.ll_team_manage: //团队管理
                startActivity(new Intent(getActivity(), TeamListActivity.class));
                break;
            case R.id.ll_promote://推广
                startActivity(new Intent(getActivity(), PromoteActivity.class));
                break;
            case R.id.ll_add_shop: //添加商铺
                startActivity(new Intent(getActivity(), AddStoresActivity.class));
                break;

        }
    }


    @Override
    public void LogisticsUserInfo(LUserInfoEntity entities) {
        myShopAdapter.setNewData(entities.getStore());
        Glide.with(getActivity()).load(Constants.MAIN_URL + entities.getHeadImgUrl()).into(mImgHead);
        mTvName.setText(entities.getRealName());
        mTvCompanyName.setText(entities.getCompanyName());
        mTvStoreServicePoint.setText(entities.getStoreServicePoint());

    }

    @Override
    public void LogisticsUserInfoErr(String s) {
        Log.i(TAG, "LogisticsUserInfoErr: " + s);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getLogisticsUserInfo("15169169195");
        mTvTitle.setText("我的");
        mTvRightText.setVisibility(View.VISIBLE);
        mTvRightText.setText("认证");
        mUserInfoEntityList = new ArrayList<>();
        myShopAdapter = new LogisticsMyShopAdapter(mUserInfoEntityList);
        mRvMyShop.setAdapter(myShopAdapter);
        mRvMyShop.setLayoutManager(new LinearLayoutManager(getActivity()));
        RvLineUtil.Line(mRvMyShop, getActivity(), 1);
        myShopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ShopDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.STORE_ID, myShopAdapter.getData().get(position).getId());
                bundle.putInt(Constants.ACTIVITY_TYPE, Constants.LOGISTICS_ME_FRAGMENT);
                intent.putExtra(Constants.ACTIVITY_TYPE, bundle);
                startActivity(intent);
            }
        });
    }


    @Override
    public void initImmersionBar() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

}
