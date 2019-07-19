package com.shanghai.logistics.ui.user_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseFragment;
import com.shanghai.logistics.base.connectors.user.UserHomeListConnector;
import com.shanghai.logistics.models.entity.user.HomeListEntity;
import com.shanghai.logistics.presenters.user.UserHomeListPresenter;
import com.shanghai.logistics.ui.user_activity.HomeDetailActivity;
import com.shanghai.logistics.ui.user_activity.SpecialLineActivity;
import com.shanghai.logistics.ui.user_activity.UserBackActivity;
import com.shanghai.logistics.ui.user_adapter.UserHomeListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserHomeFragment extends BaseFragment<UserHomeListPresenter> implements UserHomeListConnector.View {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_brand)
    TextView mTvBrand;
    @BindView(R.id.tv_special)
    TextView mTvSpecial;
    @BindView(R.id.rl_red_title)
    RelativeLayout mRlRedTitle;
    @BindView(R.id.rv_one_line)
    RecyclerView mRvOneLine;
    UserHomeListAdapter mHomeListAdapter;
    List<HomeListEntity> mHomeList;
    private static final String TAG = "UserHomeFragment";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_user;
    }


    @Override
    protected void initEventAndData() {
        mTvTitle.setText("首页");
        mPresenter.getHomeList("上海");
        Bundle bundle = getArguments();
        if (bundle != null) {
            int type = bundle.getInt(Constants.ACTIVITY_TYPE);
            switch (type) {
                case Constants.USER_HOME_FRAGMENT:
                    mRlRedTitle.setVisibility(View.GONE);
                    break;
                default:
                    mRlRedTitle.setVisibility(View.VISIBLE);
                    break;
            }
        }
        mHomeList = new ArrayList<>();
        mHomeListAdapter = new UserHomeListAdapter(mHomeList);
        mRvOneLine.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRvOneLine.setAdapter(mHomeListAdapter);
        mHomeListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), HomeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.STORE_ID, mHomeListAdapter.getData().get(position).getId());
                bundle.putInt(Constants.ACTIVITY_TYPE, Constants.USER_HOME_FRAGMENT);
                intent.putExtra(Constants.ACTIVITY_TYPE, bundle);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.img_back, R.id.tv_brand, R.id.tv_special})
    void UserOnclick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.tv_brand:
                startActivity(new Intent(getActivity(), UserBackActivity.class));
                break;
            case R.id.tv_special:
                startActivity(new Intent(getActivity(), SpecialLineActivity.class));
                break;
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void homeList(List<HomeListEntity> entities) {
        mHomeList = entities;
        mHomeListAdapter.setNewData(entities);
    }

    @Override
    public void homeListErr(String s) {
        Log.i(TAG, "homeListErr: " + s);
        ToastUtils.show(s);
    }

    @Override
    public void initImmersionBar() {
//        ImmersionBar.with(this)
//                .statusBarView(mTvStatusBar)
//                .statusBarColor(R.color.black)
//                .init();
    }


}
