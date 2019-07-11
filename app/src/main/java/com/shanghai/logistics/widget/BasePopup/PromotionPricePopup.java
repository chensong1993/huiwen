package com.shanghai.logistics.widget.BasePopup;

import android.Manifest;
import android.app.Activity;
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
import android.widget.EditText;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.util.DataUtil;
import com.shanghai.logistics.util.PhotoUtils;

import java.io.File;
import java.util.Date;

import razerdp.basepopup.BasePopupWindow;

public class PromotionPricePopup extends BasePopupWindow implements View.OnClickListener {

    Activity activity;
    public EditText mEditText;
    private ItemClickListener ItemClickListener = null;

    public interface ItemClickListener {
        void onItemClick(View view);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        ItemClickListener = itemClickListener;
    }


    public PromotionPricePopup(Activity activity) {
        super(activity);
        this.activity = activity;
        findViewById(R.id.tv_money1).setOnClickListener(this);
        findViewById(R.id.tv_money2).setOnClickListener(this);
        findViewById(R.id.tv_money3).setOnClickListener(this);
        findViewById(R.id.tv_money4).setOnClickListener(this);
        findViewById(R.id.tv_money5).setOnClickListener(this);
        findViewById(R.id.tv_money6).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        mEditText = findViewById(R.id.et_custom_amount);
        setAlignBackground(false);
        setPopupGravity(Gravity.CENTER);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(1, 1, 0, 1, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 1));
        set.addAnimation(getDefaultAlphaAnimation());
        return set;
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
        return createPopupById(R.layout.item_promote_money);
    }


    @Override
    public void onClick(View v) {
        ItemClickListener.onItemClick(v);
    }


}
