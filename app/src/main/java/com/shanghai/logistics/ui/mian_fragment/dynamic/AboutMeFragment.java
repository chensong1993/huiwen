package com.shanghai.logistics.ui.mian_fragment.dynamic;


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
 * 关于我
 */
public class AboutMeFragment extends SimpleFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about_me;
    }

    @Override
    protected void initEventAndData() {

    }

//    @OnClick({})
//    void addressOnclick(View v) {
//        switch (v.getId()) {
//            case R.id.rl_new_friend:
//
//                break;
//        }
//    }

    @Override
    public void initImmersionBar() {

    }
}
