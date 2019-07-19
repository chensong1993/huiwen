package com.shanghai.logistics.ui.main_activity.address_list;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.base.connectors.main.MainNewFriendConnector;
import com.shanghai.logistics.models.entity.main.NewFriendEntity;
import com.shanghai.logistics.presenters.main.MainNewFriendPresenter;
import com.shanghai.logistics.ui.main_adapter.MainNewFriendsAdapter;
import com.shanghai.logistics.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewFriendListActivity extends BaseActivity<MainNewFriendPresenter> implements MainNewFriendConnector.View {
    private static final String TAG = "NewFriendListActivity";
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.img_empty)
    ImageView mImgEmpty;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_myFriends)
    RecyclerView mRvMyFriends;
    List<NewFriendEntity> mNewFriendEntityList;
    MainNewFriendsAdapter mainNewFriendsAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_new_friend_list;
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("新朋友");
        mPresenter.getMainNewFriend("15169169195");
        mNewFriendEntityList=new ArrayList<>();
        mainNewFriendsAdapter=new MainNewFriendsAdapter(mNewFriendEntityList);
        mRvMyFriends.setAdapter(mainNewFriendsAdapter);
        mRvMyFriends.setLayoutManager(new LinearLayoutManager(this));
        mRvMyFriends.addItemDecoration(new RecycleViewDivider(this, LinearLayout.HORIZONTAL, 1, ContextCompat.getColor(this, R.color.hui)));

    }

    @Override
    public void MainNewFriend(List<NewFriendEntity> entities) {
        mainNewFriendsAdapter.setNewData(entities);
    }

    @Override
    public void MainNewFriendErr(String s) {
        Log.i(TAG, "MainNewFriendErr: "+s);
    }

    @OnClick({R.id.img_back, R.id.img_empty})
    void newFriendOnclick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_empty:
                break;
        }
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


}
