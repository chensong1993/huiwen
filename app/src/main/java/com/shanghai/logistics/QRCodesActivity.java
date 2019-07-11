package com.shanghai.logistics;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.yzq.zxinglibrary.encode.CodeCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class QRCodesActivity extends SimpleActivity {
     @BindView(R.id.img_QR)
    ImageView mImgQRCodes;
    @BindView(R.id.ll_QR)
    LinearLayout mLlQR;
    @BindView(R.id.tv_loading)
    TextView mTvLoading;
    Bitmap bitmap, logo;
    String url;
    String TAG = "QRCodesActivity";
    int width, height;
    DisplayMetrics dm;
    int TYPE;
    Bundle bundle;
    @Override
    protected int getLayout() {
        return R.layout.activity_qr;
    }

    @Override
    protected void initEventAndData() {
        bundle=getIntent().getBundleExtra(Constants.ACTIVITY_TYPE);
        if(bundle!=null){
            TYPE=bundle.getInt(Constants.ACTIVITY_TYPE);
            switch (TYPE){
                case Constants.OPERATING_CENTER_FRAGMENT: //物流首页

                    break;
            }
        }
        mLlQR.setVisibility(View.GONE);
        mTvLoading.setVisibility(View.VISIBLE);

        //延迟执行
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }, 200);
    }

    private void initData() {
            /*
             * 获取屏幕的宽高
             * */
            dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            width = dm.widthPixels;
            height = dm.heightPixels;
            Log.i(TAG, "initData: " + width + "  " + height);
            /*
             * 生成二维码
             * */
            url = "货车司机";
            Glide.with(this).asBitmap().load(R.mipmap.ic_launcher).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    mTvLoading.setVisibility(View.GONE);
                    logo = resource;
                    bitmap = CodeCreator.createQRCode(url, width, width, logo);
                    if (bitmap != null) {
                        mLlQR.setVisibility(View.VISIBLE);
                        mImgQRCodes.setImageBitmap(bitmap);
                    }
                }
            });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @OnClick({R.id.img_back, R.id.tv_save_photo})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_save_photo:
                autoObtainStoragePermission();
                break;
        }
    }

    /**
     * 保存图片
     */
    public void saveImageToGallery(Bitmap bmp) {
        //生成路径
        String root = Environment.getExternalStorageDirectory().getPath();
        String dirName = "QRCodes";
        File appDir = new File(root, dirName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }

        //文件名为时间
        long timeStamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(timeStamp));
        String fileName = sd + ".png";

        //获取文件
        File file = new File(appDir, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            //通知系统相册刷新
            this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.fromFile(new File(file.getPath()))));
            ToastUtils.show("保存成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 自动获取sdk权限
     */
    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.STORAGE_PERMISSIONS_REQUEST_CODE);
            }
        } else {
            saveImageToGallery(bitmap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constants.STORAGE_PERMISSIONS_REQUEST_CODE://调用系统相册申请Sdcard权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    saveImageToGallery(bitmap);
                } else {
                    ToastUtils.show( "请允许打操作SDCard！");

                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
