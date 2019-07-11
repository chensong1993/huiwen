package com.shanghai.logistics.ui.user_fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.main_activity.address_list.GroupingActivity;
import com.shanghai.logistics.ui.main_activity.address_list.PersonalInforActivity;
import com.shanghai.logistics.ui.user_activity.linkman.LinkManDetailActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 联系人
 */
public class UserLinkmanFragment extends SimpleFragment {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.rl_friend)
    RelativeLayout mRlFriend;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_linkman_user;
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("联系人");
    }

    @OnClick({R.id.img_back, R.id.rl_friend})
    void GroupingOnclick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.rl_friend: //
                startActivity(new Intent(getContext(), LinkManDetailActivity.class));
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // App.refWatcher.watch(this);
    }

//    @Override
//    protected void initInject() {
//        getFragmentComponent().inject(this);
//    }


    @Override
    public void initImmersionBar() {

    }
}
