package com.shanghai.logistics.widget.BasePopup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.ui.user_activity.home_detail.VehicleLengthActivity;
import com.shanghai.logistics.ui.user_activity.release.SendOrdersActivity;
import com.shanghai.logistics.ui.user_activity.release.ShipmentsActivity;

import razerdp.basepopup.BasePopupWindow;

public class MenuPopup extends BasePopupWindow implements View.OnClickListener {
//    private RelativeLayout mRlGOyun;
//    private RelativeLayout mRlAddFriend;
//    private RelativeLayout mRlQR;

    // Context mContext;
    Activity activity;

    public MenuPopup(Activity activity) {
        super(activity);
        this.activity = activity;
        findViewById(R.id.rl_sweep).setOnClickListener(this);
        findViewById(R.id.rl_send_orders).setOnClickListener(this);
        findViewById(R.id.rl_shipments).setOnClickListener(this);
        setAlignBackground(false);
        setPopupGravity(Gravity.BOTTOM | Gravity.LEFT);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
        set.addAnimation(getDefaultAlphaAnimation());
        return set;
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
        set.addAnimation(getDefaultAlphaAnimation(false));
        return set;
    }

    @Override
    public void showPopupWindow(View v) {
        setOffsetX(v.getWidth() / 2);
        super.showPopupWindow(v);
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.item_release_popup);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_sweep:

                break;
            case R.id.rl_send_orders:
                activity.startActivity(new Intent(activity, SendOrdersActivity.class));
                break;
            case R.id.rl_shipments:
                activity.startActivity(new Intent(activity, ShipmentsActivity.class));
                break;
            default:
                break;

        }

    }
}
