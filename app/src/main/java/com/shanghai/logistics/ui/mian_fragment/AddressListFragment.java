package com.shanghai.logistics.ui.mian_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseFragment;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.base.connectors.main.MainFriendConnector;
import com.shanghai.logistics.models.entity.main.FriendEntity;
import com.shanghai.logistics.presenters.main.MainFriendPresenter;
import com.shanghai.logistics.ui.main_activity.address_list.GroupingActivity;
import com.shanghai.logistics.ui.main_activity.address_list.NewFriendListActivity;
import com.shanghai.logistics.ui.main_activity.address_list.PersonalInforActivity;
import com.shanghai.logistics.ui.main_adapter.MainFriendsAdapter;
import com.shanghai.logistics.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 通讯录
 */
public class AddressListFragment extends BaseFragment<MainFriendPresenter> implements MainFriendConnector.View {

    private static final String TAG = "AddressListFragment";
    @BindView(R.id.tv_head_title)
    TextView mTvHeadTitle;
    @BindView(R.id.img_search)
    ImageView mImgSearch;
    @BindView(R.id.rl_new_friend)
    RelativeLayout mRlNewFriend;
    @BindView(R.id.rl_type)
    RelativeLayout mRlType;
    @BindView(R.id.rv_friend)
    RecyclerView mRvFriend;
    MainFriendsAdapter mainFriendsAdapter;
    List<FriendEntity> mFriendEntityList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_address_list;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getMainFriend("15169169195");
        mTvHeadTitle.setText("通讯录");
        mFriendEntityList = new ArrayList<>();
        mainFriendsAdapter = new MainFriendsAdapter(mFriendEntityList);
        mRvFriend.setAdapter(mainFriendsAdapter);
        mRvFriend.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvFriend.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayout.HORIZONTAL, 1, ContextCompat.getColor(getActivity(), R.color.hui)));

    }

    @Override
    public void MainFriend(List<FriendEntity> entities) {
        for (int i = 0; i <entities.size() ; i++) {
            Log.i(TAG, "MainFriend: "+entities.get(i).getPhone());
        }

        mainFriendsAdapter.setNewData(entities);
        mainFriendsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), PersonalInforActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constants.PHONE, mainFriendsAdapter.getData().get(position).getPhone());
                bundle.putString(Constants.NICK_NAME, mainFriendsAdapter.getData().get(position).getNameRemark());
                bundle.putString(Constants.HEAD_URL, Constants.MAIN_URL + mainFriendsAdapter.getData().get(position).getHeadUrl());
                intent.putExtra(Constants.All_VALUE, bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void MainFriendErr(String s) {
        Log.i(TAG, "MainFriendErr: " + s);
    }

    @OnClick({R.id.rl_new_friend, R.id.rl_type})
    void addressOnclick(View v) {
        switch (v.getId()) {
            //新朋友
            case R.id.rl_new_friend:
                startActivity(new Intent(getContext(), NewFriendListActivity.class));
                break;
            //分组
            case R.id.rl_type:
                startActivity(new Intent(getContext(), GroupingActivity.class));
                break;
        }
    }

    @Override
    public void initImmersionBar() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }


}
