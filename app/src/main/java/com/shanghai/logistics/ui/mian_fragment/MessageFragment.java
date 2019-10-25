package com.shanghai.logistics.ui.mian_fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.DriverActivity;
import com.shanghai.logistics.LogisticsActivity;
import com.shanghai.logistics.R;
import com.shanghai.logistics.UserActivity;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.util.DestroyActivityUtil;
import com.shanghai.logistics.widget.BasePopup.MessagePopup;

import butterknife.BindView;
import butterknife.OnClick;
import razerdp.basepopup.QuickPopupBuilder;
import razerdp.basepopup.QuickPopupConfig;

/**
 * 信牙信息首页
 */
@SuppressLint("ValidFragment")
public class MessageFragment extends SimpleFragment {

    @BindView(R.id.go_yun)
    RelativeLayout mRlGoYun;
    @BindView(R.id.rl_group_chat)
    RelativeLayout mRlChatList;
    @BindView(R.id.img_search)
    ImageView mImgSearch;
    @BindView(R.id.img_menu)
    ImageView mImgMenu;
    int userType;
    static final String TAG = MessageFragment.class.getName();
    Bundle bundle;
    String mLoginPhone;

    public MessageFragment() {
    }

    @SuppressLint("ValidFragment")
    public MessageFragment(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initEventAndData() {
        mImgSearch.setVisibility(View.VISIBLE);
        userType = App.kv.decodeInt(Constants.SELECTED_USER_TYPE);
        Log.i(TAG, "initEventAndData: " + userType);

        if(bundle!=null){
            mLoginPhone=bundle.getString(Constants.PHONE);
            Log.i(TAG, "initEventAndData: " + mLoginPhone);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        userType = App.kv.decodeInt(Constants.SELECTED_USER_TYPE);
        Log.i(TAG, "initEventAndData: " + userType);
    }

    @OnClick({R.id.go_yun, R.id.rl_group_chat, R.id.img_menu})
    void mainOnclick(View v) {
        switch (v.getId()) {
            case R.id.go_yun:
                switch (userType) {
                    case Constants.USER_CLIENT:
                        startActivity(new Intent(getContext(), UserActivity.class));
                        break;
                    case Constants.DRIVER_CLIENT:
                        startActivity(new Intent(getContext(), DriverActivity.class));
                        break;
                    case Constants.LOGISTICS_CLIENT:
                        startActivity(new Intent(getContext(), LogisticsActivity.class));
                        break;
                    default:
                        startActivity(new Intent(getContext(), UserActivity.class));
                        break;
                }
                break;
            case R.id.rl_group_chat:
                break;
            case R.id.img_menu:
                MessagePopup popup = new MessagePopup(getActivity());
                popup.setBackgroundColor(R.color.transparent);
                popup.showPopupWindow(mImgMenu);
                break;
        }
    }

    @Override
    public void initImmersionBar() {

    }
}