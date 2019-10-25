package com.shanghai.logistics.widget.BasePopup;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.ui.user_activity.release.SendOrdersActivity;
import com.shanghai.logistics.ui.user_activity.release.ShipmentsActivity;

import razerdp.basepopup.BasePopupWindow;

public class MessagePopup extends BasePopupWindow implements View.OnClickListener {
//    private RelativeLayout mRlGOyun;
//    private RelativeLayout mRlAddFriend;
//    private RelativeLayout mRlQR;

  // Context mContext;
  Activity activity;

  public MessagePopup(Activity activity) {
    super(activity);
    this.activity = activity;
    findViewById(R.id.go_yun).setOnClickListener(this);
    findViewById(R.id.rl_add_friend).setOnClickListener(this);
    findViewById(R.id.rl_qr).setOnClickListener(this);
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
    return createPopupById(R.layout.item_head_popup);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.go_yun:
        ToastUtils.show("发起群聊");
        break;
      case R.id.rl_add_friend:
        ToastUtils.show("添加好友");
        //activity.startActivity(new Intent(activity, SendOrdersActivity.class));
        break;
      case R.id.rl_qr:
        // activity.startActivity(new Intent(activity, ShipmentsActivity.class));
        break;
      default:
        break;

    }

  }
}