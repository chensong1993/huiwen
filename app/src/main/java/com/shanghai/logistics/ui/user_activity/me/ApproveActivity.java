package com.shanghai.logistics.ui.user_activity.me;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.example.media.MediaSelector;
import com.example.media.bean.MediaSelectorFile;
import com.example.media.resolver.Contast;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.util.DataUtil;
import com.shanghai.logistics.util.ImageUtil;
import com.shanghai.logistics.util.PhotoUtils;
import com.shanghai.logistics.widget.BasePopup.ImagePopup;

import java.io.File;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 认证
 * */
public class ApproveActivity extends SimpleActivity {
    static final String TAG = ApproveActivity.class.getName();
    @BindView(R.id.img_identity)
    ImageView mImgIdentity;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    File nativeFile;
    Uri imageUri;
    MediaSelector.MediaOptions mediaOptions;

    @Override
    protected int getLayout() {
        return R.layout.activity_approve;
    }


    @Override
    protected void initEventAndData() {
        mTvTitle.setText("认证");
    }


    @OnClick({R.id.img_identity, R.id.img_back})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_identity:
                //Activity中
                ImagePopup imagePopup = new ImagePopup(this);
                imagePopup.setItemClickListener(new ImagePopup.ItemClickListener() {
                    @Override
                    public void onItemClick(View view) {
                        switch (view.getId()) {
                            case R.id.tv_photo:
                                autoObtainCameraPermission();
                                imagePopup.dismiss();
                                break;
                            case R.id.tv_album:
                                /***
                                 *自定义选择图片方式
                                 */
                                mediaOptions = new MediaSelector.MediaOptions();
                                mediaOptions.maxChooseMedia = 1;
                                //是否要显示拍照功能
                                mediaOptions.isShowCamera = true;
                                //是否要压缩
                                mediaOptions.isCompress = false;
                                //是否要显示视频文件
                                mediaOptions.isShowVideo = false;
                                //设置module主题
                                mediaOptions.themeColor = R.color.black;
                                //设置要不要裁剪（视频不裁剪、单图选择接受裁剪，裁剪大小自己可以设置）
                                mediaOptions.isCrop = true;
                                MediaSelector.with(ApproveActivity.this).setMediaOptions(mediaOptions).openMediaActivity();
                                imagePopup.dismiss();
                                break;
                            case R.id.tv_cancel:
                                imagePopup.dismiss();
                                break;
                            default:
                                break;

                        }
                    }
                });
                imagePopup.showPopupWindow();
                break;
        }
    }


    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    ToastUtils.show("您已经拒绝过一次");
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.CAMERA_PERMISSIONS_REQUEST_CODE);
            }
        } else {//有权限直接调用系统相机拍照
            if (PhotoUtils.hasSdcard()) {
                String time = DataUtil.dateToStr(new Date());
                nativeFile = new File(Environment.getExternalStorageDirectory().getPath() + "/+" + time + ".jpg");
                imageUri = Uri.fromFile(nativeFile);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, nativeFile);//通过FileProvider创建一个content类型的Uri
                PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
            } else {
                ToastUtils.show("设备没有SD卡！");
            }
        }
    }

    /**
     * 响应权限
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Constants.CAMERA_PERMISSIONS_REQUEST_CODE: //调用系统相机申请拍照权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (PhotoUtils.hasSdcard()) {
                        String time = DataUtil.dateToStr(new Date());
                        nativeFile = new File(Environment.getExternalStorageDirectory().getPath() + "/+" + time + ".jpg");
                        imageUri = Uri.fromFile(nativeFile);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, nativeFile);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
                    } else {
                        ToastUtils.show("设备没有SD卡！");
                    }
                } else {
                    ToastUtils.show("请允许打开相机！");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.CAMERA_PERMISSIONS_REQUEST_CODE);
                    }
                }
                break;

        }
    }

    /**
     * 选择图片结果回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //从相册获取
        if (resultCode == Contast.CODE_RESULT_MEDIA && requestCode == Contast.CODE_REQUEST_MEDIA) {
            List<MediaSelectorFile> mediaList = MediaSelector.resultMediaFile(data);
            for (int i = 0; i < mediaList.size(); i++) {
                Glide.with(this).load(mediaList.get(i).filePath).into(mImgIdentity);
                Log.i(TAG, "onActivityResult: " + mediaList.get(i).fileName);

            }
        }
        if (requestCode == Constants.CODE_CAMERA_REQUEST) {
            if (nativeFile != null) {
                disposePhoto(nativeFile);
                Log.i(TAG, "onActivityResult: " + nativeFile);
                Glide.with(this).load(nativeFile).into(mImgIdentity);
            } else {
                ToastUtils.show("获取照片失败");
            }
        }
    }

    /**
     * 处理压缩处理图片文件
     *
     * @param file
     * @return 返回bitmap
     */
    private Bitmap disposePhoto(File file) {
        Bitmap bitmap;
        if (file.length() / 1024 > 500) {
            bitmap = ImageUtil.compressImage(ImageUtil.filePathToBitmap(file.getPath(), 2));
        } else if (file.length() / 1024 <= 500 && file.length() / 1024 > 200) {
            bitmap = ImageUtil.compressImage(ImageUtil.filePathToBitmap(file.getPath()));
        } else {
            bitmap = ImageUtil.filePathToBitmap(file.getPath());
        }
        ImageUtil.saveBitmapFile(bitmap, file.getPath());
        // upload(file);//联网上传图片
        return bitmap;
    }


}
