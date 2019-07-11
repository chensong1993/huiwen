package com.shanghai.logistics.widget.BasePopup;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;

import com.example.media.MediaSelector;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.ui.user_activity.release.SendOrdersActivity;
import com.shanghai.logistics.ui.user_activity.release.ShipmentsActivity;
import com.shanghai.logistics.util.DataUtil;
import com.shanghai.logistics.util.PhotoUtils;

import java.io.File;
import java.util.Date;

import razerdp.basepopup.BasePopupWindow;

public class ImagePopup extends BasePopupWindow implements View.OnClickListener {

    Activity activity;

    private ItemClickListener ItemClickListener = null;

    public interface ItemClickListener {
        void onItemClick(View view);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        ItemClickListener = itemClickListener;
    }


    public ImagePopup(Activity activity) {
        super(activity);
        this.activity = activity;
        findViewById(R.id.tv_photo).setOnClickListener(this);
        findViewById(R.id.tv_album).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        setAlignBackground(false);
        setPopupGravity(Gravity.BOTTOM);
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
        return createPopupById(R.layout.item_image_select);
    }


    @Override
    public void onClick(View v) {
        ItemClickListener.onItemClick(v);
    }


    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (activity.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    ToastUtils.show("您已经拒绝过一次");
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.CAMERA_PERMISSIONS_REQUEST_CODE);
            }
        } else {//有权限直接调用系统相机拍照
            if (PhotoUtils.hasSdcard()) {
                String time = DataUtil.dateToStr(new Date());
                File nativeFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + time + ".jpg");
                Uri imageUri = Uri.fromFile(nativeFile);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    Log.i("autoObtainCamera", imageUri.getPath());
                imageUri = FileProvider.getUriForFile(activity, Constants.FILE_PROVIDER, nativeFile);//通过FileProvider创建一个content类型的Uri
                PhotoUtils.takePicture(activity, imageUri, Constants.CODE_CAMERA_REQUEST);
            } else {
                ToastUtils.show("设备没有SD卡！");
            }
        }
    }
}
