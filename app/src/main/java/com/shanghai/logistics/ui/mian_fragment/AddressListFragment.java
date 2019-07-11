package com.shanghai.logistics.ui.mian_fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.main_activity.address_list.GroupingActivity;
import com.shanghai.logistics.ui.main_activity.address_list.NewFriendListActivity;
import com.shanghai.logistics.ui.main_activity.address_list.PersonalInforActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 通讯录
 */
public class AddressListFragment extends SimpleFragment {
    @BindView(R.id.tv_head_title)
    TextView mTvHeadTitle;
    @BindView(R.id.img_search)
    ImageView mImgSearch;
    @BindView(R.id.rl_new_friend)
    RelativeLayout mRlNewFriend;
    @BindView(R.id.rl_type)
    RelativeLayout mRlType;
    @BindView(R.id.rl_friend)
    RelativeLayout mRlFriend;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_address_list;
    }

    @Override
    protected void initEventAndData() {
        mTvHeadTitle.setText("通讯录");
      //  mImgSearch.setVisibility(View.GONE);
    }

    @OnClick({R.id.rl_new_friend, R.id.rl_type, R.id.rl_friend})
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
            case R.id.rl_friend:
                startActivity(new Intent(getContext(), PersonalInforActivity.class));
                break;
        }
    }

    @Override
    public void initImmersionBar() {

    }
}
