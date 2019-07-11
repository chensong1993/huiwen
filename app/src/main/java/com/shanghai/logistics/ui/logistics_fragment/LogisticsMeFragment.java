package com.shanghai.logistics.ui.logistics_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanghai.logistics.QRCodesActivity;
import com.shanghai.logistics.R;
import com.shanghai.logistics.SwitchingIdentityActivity;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.logistics_activity.AddShopActivity;
import com.shanghai.logistics.ui.logistics_activity.AddStoresActivity;
import com.shanghai.logistics.ui.logistics_activity.CertificationActivity;
import com.shanghai.logistics.ui.logistics_activity.PromoteActivity;
import com.shanghai.logistics.ui.logistics_activity.TeamListActivity;
import com.shanghai.logistics.ui.user_activity.MoreCommentsActivity;
import com.shanghai.logistics.ui.user_activity.ShopDetailActivity;
import com.shanghai.logistics.ui.user_activity.me.AddressListActivity;
import com.shanghai.logistics.ui.user_activity.me.ApproveActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 */
public class LogisticsMeFragment extends SimpleFragment {

    @BindView(R.id.tv_right_content)
    TextView mTvRightText;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
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
    @BindView(R.id.ll_shop_detail)
    LinearLayout mLlShopDetail;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_logistics_me;
    }

    @OnClick({R.id.tv_qr, R.id.ll_shop_detail, R.id.ll_add_shop, R.id.ll_promote, R.id.ll_team_manage, R.id.ll_comment_manage, R.id.img_back, R.id.tv_right_content, R.id.ll_address_list, R.id.ll_switching_identity})
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
            case R.id.ll_shop_detail: //商铺详情
                Intent intent = new Intent(getActivity(), ShopDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.ACTIVITY_TYPE, Constants.LOGISTICS_ME_FRAGMENT);
                intent.putExtra(Constants.ACTIVITY_TYPE, bundle);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("我的");
        mTvRightText.setVisibility(View.VISIBLE);
        mTvRightText.setText("认证");
    }


    @Override
    public void initImmersionBar() {

    }
}
