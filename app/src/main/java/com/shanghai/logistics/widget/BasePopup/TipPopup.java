package com.shanghai.logistics.widget.BasePopup;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.util.DataUtil;
import com.shanghai.logistics.util.PhotoUtils;

import java.io.File;
import java.util.Date;

import razerdp.basepopup.BasePopupWindow;

public class TipPopup extends BasePopupWindow implements View.OnClickListener {

    Activity activity;
    private ItemClickListener ItemClickListener = null;

    public interface ItemClickListener {
        void onItemClick(View view);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        ItemClickListener = itemClickListener;
    }


    public TipPopup(Activity activity) {
        super(activity);
        this.activity = activity;

        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        setAlignBackground(false);
        setPopupGravity(Gravity.CENTER);
    }



    @Override
    protected Animation onCreateDismissAnimation() {
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(1, 1, 1, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 1));
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
        return createPopupById(R.layout.item_tip);
    }


    @Override
    public void onClick(View v) {
        ItemClickListener.onItemClick(v);
    }


}
