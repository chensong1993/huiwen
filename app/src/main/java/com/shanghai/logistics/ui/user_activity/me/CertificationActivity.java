package com.shanghai.logistics.ui.user_activity.me;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.media.MediaSelector;
import com.example.media.bean.MediaSelectorFile;
import com.example.media.resolver.Contast;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.base.connectors.user.UserCertificationConnector;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.services.ApiService;
import com.shanghai.logistics.models.services.UserService;
import com.shanghai.logistics.presenters.user.UserCertificationPresenter;
import com.shanghai.logistics.util.DataUtil;
import com.shanghai.logistics.util.FileUploadUtil;
import com.shanghai.logistics.util.ImageUtil;
import com.shanghai.logistics.util.PhotoUtils;
import com.shanghai.logistics.widget.BasePopup.ImagePopup;
import com.shanghai.logistics.widget.CommonSubscriber;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/*
 * 认证
 * */
public class CertificationActivity extends SimpleActivity {
    static final String TAG = CertificationActivity.class.getName();
    @BindView(R.id.img_identity)
    ImageView mImgIdentity;
    @BindView(R.id.img_identity_card)
    ImageView mImgIdentityCard;
    @BindView(R.id.img_identity_card_b)
    ImageView mImgIdentityCardB;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_id_card_num)
    EditText mEtIdCareNum;
    File nativeFile, ZFile, HFile;
    Uri imageUri;
    int typeFile;
    MediaSelector.MediaOptions mediaOptions;
    ImagePopup imagePopup;

    @Override
    protected int getLayout() {
        return R.layout.activity_approve;
    }


    @Override
    protected void initEventAndData() {
        mTvTitle.setText("认证");
    }


    @OnClick({R.id.img_identity, R.id.img_back, R.id.tv_certification, R.id.img_identity_card, R.id.img_identity_card_b})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_certification:
                uploadInfo();
                Log.i(TAG, "onItemClick: ");
                break;
            case R.id.img_identity: //上身照
                ImgUpload(0);
                break;
            case R.id.img_identity_card: //身份证正面
                ImgUpload(1);
                break;
            case R.id.img_identity_card_b: //身份证背面
                ImgUpload(2);
                break;
        }
    }


    void ImgUpload(int type) {
        typeFile = type;
        imagePopup = new ImagePopup(this);
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
                        MediaSelector.with(CertificationActivity.this).setMediaOptions(mediaOptions).openMediaActivity();
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
                switch (typeFile) {
                    case 0:
                        nativeFile = new File(Environment.getExternalStorageDirectory().getPath() + "/+" + time + ".jpg");
                        imageUri = Uri.fromFile(nativeFile);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, nativeFile);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
                        break;
                    case 1:
                        ZFile = new File(Environment.getExternalStorageDirectory().getPath() + "/+" + time + ".jpg");
                        imageUri = Uri.fromFile(ZFile);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, ZFile);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
                        break;
                    case 2:
                        HFile = new File(Environment.getExternalStorageDirectory().getPath() + "/+" + time + ".jpg");
                        imageUri = Uri.fromFile(HFile);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, HFile);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
                        break;
                }
//                nativeFile = new File(Environment.getExternalStorageDirectory().getPath() + "/+" + time + ".jpg");
//                imageUri = Uri.fromFile(nativeFile);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//                    imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, nativeFile);//通过FileProvider创建一个content类型的Uri
//                PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
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
                        switch (typeFile) {
                            case 0:
                                nativeFile = new File(Environment.getExternalStorageDirectory().getPath() + "/+" + time + ".jpg");
                                imageUri = Uri.fromFile(nativeFile);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                                    imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, nativeFile);//通过FileProvider创建一个content类型的Uri
                                PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
                                break;
                            case 1:
                                ZFile = new File(Environment.getExternalStorageDirectory().getPath() + "/+" + time + ".jpg");
                                imageUri = Uri.fromFile(ZFile);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                                    imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, ZFile);//通过FileProvider创建一个content类型的Uri
                                PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
                                break;
                            case 2:
                                HFile = new File(Environment.getExternalStorageDirectory().getPath() + "/+" + time + ".jpg");
                                imageUri = Uri.fromFile(HFile);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                                    imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, HFile);//通过FileProvider创建一个content类型的Uri
                                PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
                                break;
                        }

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
            switch (typeFile) {
                case 0:
                    for (int i = 0; i < mediaList.size(); i++) {
                        nativeFile = new File(mediaList.get(i).filePath);
                        Glide.with(this).load(mediaList.get(i).filePath).into(mImgIdentity);
                        Log.i(TAG, "onActivityResult: " + nativeFile.getAbsolutePath());

                    }
                    break;
                case 1:
                    for (int i = 0; i < mediaList.size(); i++) {
                        ZFile = new File(mediaList.get(i).filePath);
                        Glide.with(this).load(mediaList.get(i).filePath).into(mImgIdentityCard);
                        Log.i(TAG, "onActivityResult: " + ZFile.getAbsolutePath());

                    }
                    break;
                case 2:
                    for (int i = 0; i < mediaList.size(); i++) {
                        HFile = new File(mediaList.get(i).filePath);
                        Glide.with(this).load(mediaList.get(i).filePath).into(mImgIdentityCardB);
                        Log.i(TAG, "onActivityResult: " + HFile.getAbsolutePath());

                    }
                    break;
            }

        }
        if (requestCode == Constants.CODE_CAMERA_REQUEST) {
            switch (typeFile) {
                case 0:
                    if (nativeFile != null) {
                        Log.i(TAG, "onActivityResult: " + nativeFile);
                        Glide.with(this).load(nativeFile).into(mImgIdentity);
                    } else {
                        ToastUtils.show("获取照片失败");
                    }
                    break;
                case 1:
                    if (ZFile != null) {
                        Log.i(TAG, "onActivityResult: " + ZFile);
                        Glide.with(this).load(ZFile).into(mImgIdentityCard);
                    } else {
                        ToastUtils.show("获取照片失败");
                    }
                    break;
                case 2:
                    if (HFile != null) {
                        Log.i(TAG, "onActivityResult: " + HFile);
                        Glide.with(this).load(HFile).into(mImgIdentityCardB);
                    } else {
                        ToastUtils.show("获取照片失败");
                    }
                    break;
            }

        }
    }

    @SuppressLint("CheckResult")
    private void uploadInfo() {
        File[] files = {nativeFile, ZFile, HFile};
        Map<String, RequestBody> map = new HashMap<>();
        String[] fileName = {"headImgUrl", "IDCardImgFront", "IDCardImgReverse"};
        map.put("phone", FileUploadUtil.requestBody("17621147953"));
        map.put("IDCardNum", FileUploadUtil.requestBody(mEtIdCareNum.getText().toString()));
        map.put("realName", FileUploadUtil.requestBody(mEtName.getText().toString()));
        Log.i(TAG, "uploadInfo: "+nativeFile.getPath());
        ApiService.getInstance()
                .create(UserService.class, Constants.MAIN_URL)
                .userCertification(FileUploadUtil.uploadInfo(fileName, map, files))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<ApiResponse<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //  Log.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onSuccess(ApiResponse<Integer> integer) {
                        switch (integer.getMsg()) {
                            case 0:
                                ToastUtils.show("上传失败");
                                break;
                            case 1:
                                ToastUtils.show("上传成功");
                                break;
                            case -1:
                                ToastUtils.show("参数不能为空");
                                break;
                            case -2:
                                ToastUtils.show("账号已经注册过");
                                break;
                        }
                        Log.i(TAG, "onSuccess: " + integer.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                    }

                });
    }




}
