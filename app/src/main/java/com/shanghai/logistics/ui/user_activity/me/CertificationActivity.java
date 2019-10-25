package com.shanghai.logistics.ui.user_activity.me;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.media.MediaSelector;
import com.example.media.bean.MediaSelectorFile;
import com.example.media.resolver.Contast;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.connectors.user.UserPersonalCertificationConnector;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.user.PersonalCertification;
import com.shanghai.logistics.models.http.Uploading;
import com.shanghai.logistics.models.services.ApiService;
import com.shanghai.logistics.models.services.UserService;
import com.shanghai.logistics.presenters.user.UserPersonalCertificationPresenter;
import com.shanghai.logistics.util.DataUtil;
import com.shanghai.logistics.util.FileUploadUtil;
import com.shanghai.logistics.util.PhotoUtils;
import com.shanghai.logistics.widget.BasePopup.ImagePopup;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
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
import okhttp3.RequestBody;

/*
 * 用户认证
 * */
public class CertificationActivity extends BaseActivity<UserPersonalCertificationPresenter> implements UserPersonalCertificationConnector.View {
    static final String TAG = "CertificationActivity";
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    @BindView(R.id.ll_top_t)
    LinearLayout mLlTopT;
    @BindView(R.id.img_identity)
    ImageView mImgIdentity;
    @BindView(R.id.img_identity_card)
    ImageView mImgIdentityCard;
    @BindView(R.id.img_identity_card_b)
    ImageView mImgIdentityCardB;

    @BindView(R.id.img_identity_t)
    ImageView mImgIdentityT;
    @BindView(R.id.img_identity_card_t)
    ImageView mImgIdentityCardT;
    @BindView(R.id.img_identity_card_b_t)
    ImageView mImgIdentityCardBT;

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_certification)
    TextView mTvConfirm;
    @BindView(R.id.et_name)
    TextView mTvName;
    @BindView(R.id.et_id_card_num)
    TextView mTvIdCareNum;

    @BindView(R.id.et_name_t)
    EditText mEtName;
    @BindView(R.id.et_id_card_num_t)
    EditText mEtIdCareNum;
    @BindView(R.id.tv_certification_t)
    TextView mTvConfirmT;
    File nativeFile, ZFile, HFile;
    Uri imageUri;
    int typeFile;
    MediaSelector.MediaOptions mediaOptions;
    ImagePopup imagePopup;
    int isCertification;

    @Override
    protected int getLayout() {
        return R.layout.activity_approve;
    }


    @Override
    protected void initEventAndData() {
        mTvTitle.setText("认证");
        //查询是否认证通过
        mPresenter.getUserInfo(mLoginPhone);
    }


    @OnClick({R.id.tv_certification_t, R.id.img_identity_t, R.id.img_back, R.id.tv_certification, R.id.img_identity_card_t, R.id.img_identity_card_b_t})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_certification: //跳转到修改资料
                mLlTopT.setVisibility(View.VISIBLE);
                mLlTop.setVisibility(View.GONE);
                mTvConfirmT.setVisibility(View.VISIBLE);
                mTvConfirm.setVisibility(View.GONE);
                Log.i(TAG, "onItemClick: ");
                break;
            case R.id.tv_certification_t: //提交修改资料
                if (nativeFile == null) {
                    ToastUtils.show("头像未上传");
                    return;
                }
                if (ZFile == null) {
                    ToastUtils.show("身份证正面未上传");
                    return;
                }
                if (HFile == null) {
                    ToastUtils.show("身份证背面未上传");
                    return;
                }

                if (mEtName.getText().toString().isEmpty()) {
                    ToastUtils.show("未填写姓名");
                    return;
                }
                if (mEtIdCareNum.getText().toString().isEmpty()) {
                    ToastUtils.show("未填写身份证信息");
                    return;
                }
                if (isCertification == 1) {
                    changeInfo();
                } else {
                    uploadInfo();
                }
                break;
            case R.id.img_identity_t: //上身照
                ImgUpload(0);
                break;
            case R.id.img_identity_card_t: //身份证正面
                ImgUpload(1);
                break;
            case R.id.img_identity_card_b_t: //身份证背面
                ImgUpload(2);
                break;
        }
    }

    @Override
    public void UserInfo(PersonalCertification userInfo) {
        isCertification = 1;
        mTvConfirm.setText("修改认证");
        Glide.with(this).load(Constants.MAIN_URL + userInfo.getHeadImgUrl()).into(mImgIdentity);
        Glide.with(this).load(Constants.MAIN_URL + userInfo.getIDCardImgFront()).into(mImgIdentityCard);
        Glide.with(this).load(Constants.MAIN_URL + userInfo.getIDCardImgReverse()).into(mImgIdentityCardB);
        mTvIdCareNum.setText(userInfo.getIDCardNum());
        mTvName.setText(userInfo.getRealName());
        mLlTop.setVisibility(View.VISIBLE);
        mLlTopT.setVisibility(View.GONE);
        mTvConfirm.setVisibility(View.VISIBLE);

        Log.i(TAG, "UserInfo: "+userInfo.toString());
    }

    @Override
    public void UserInfoErr(String s) {
        int msg = Integer.valueOf(s);
        Log.i(TAG, "UserInfoErr: " + msg);
        switch (msg) {
            case 0:
                isCertification = 0;
                mLlTop.setVisibility(View.GONE);
                mLlTopT.setVisibility(View.VISIBLE);
                mTvConfirm.setVisibility(View.GONE);
                mTvConfirmT.setVisibility(View.VISIBLE);
                mTvConfirmT.setText("提交审核");
                break;
            case -1:
                isCertification = -1;
                mLlTop.setVisibility(View.GONE);
                mLlTopT.setVisibility(View.VISIBLE);
                mTvConfirm.setVisibility(View.GONE);
                mTvConfirmT.setVisibility(View.VISIBLE);
                mTvConfirmT.setText("提交审核");
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
                        nativeFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + time + ".jpg");
                        imageUri = Uri.fromFile(nativeFile);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, nativeFile);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
                        break;
                    case 1:
                        ZFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + time + ".jpg");
                        imageUri = Uri.fromFile(ZFile);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, ZFile);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
                        break;
                    case 2:
                        HFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + time + ".jpg");
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
                        Glide.with(this).load(mediaList.get(i).filePath).into(mImgIdentityT);
                        Log.i(TAG, "onActivityResult: " + nativeFile.getAbsolutePath());

                    }
                    break;
                case 1:
                    for (int i = 0; i < mediaList.size(); i++) {
                        ZFile = new File(mediaList.get(i).filePath);
                        Glide.with(this).load(mediaList.get(i).filePath).into(mImgIdentityCardT);
                        Log.i(TAG, "onActivityResult: " + ZFile.getAbsolutePath());

                    }
                    break;
                case 2:
                    for (int i = 0; i < mediaList.size(); i++) {
                        HFile = new File(mediaList.get(i).filePath);
                        Glide.with(this).load(mediaList.get(i).filePath).into(mImgIdentityCardBT);
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
                        Glide.with(this).load(nativeFile).into(mImgIdentityT);
                    } else {
                        ToastUtils.show("获取照片失败");
                    }
                    break;
                case 1:
                    if (ZFile != null) {
                        Log.i(TAG, "onActivityResult: " + ZFile);
                        Glide.with(this).load(ZFile).into(mImgIdentityCardT);
                    } else {
                        ToastUtils.show("获取照片失败");
                    }
                    break;
                case 2:
                    if (HFile != null) {
                        Log.i(TAG, "onActivityResult: " + HFile);
                        Glide.with(this).load(HFile).into(mImgIdentityCardBT);
                    } else {
                        ToastUtils.show("获取照片失败");
                    }
                    break;
            }

        }
    }

    //提交认证
    @SuppressLint("CheckResult")
    private void uploadInfo() {
        Uploading.UserCertification(nativeFile, ZFile, HFile, mLoginPhone, mEtIdCareNum.getText().toString(),
                mEtName.getText().toString(), new SingleObserver<ApiResponse<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ApiResponse<Integer> integer) {
                        switch (integer.getMsg()) {
                            case 0:
                                ToastUtils.show("上传失败");
                                break;
                            case 1:
                                ToastUtils.show("上传成功");
                                finish();
                                break;
                            case -1:
                                ToastUtils.show("参数不能为空");
                                break;
                            default:
                                ToastUtils.show("上传失败");
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

    //修改信息
    @SuppressLint("CheckResult")
    private void changeInfo() {
        Uploading.UserUpCertification(nativeFile, ZFile, HFile, mLoginPhone, mEtIdCareNum.getText().toString(),
                mEtName.getText().toString(), new SingleObserver<ApiResponse<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ApiResponse<Integer> integer) {
                        switch (integer.getMsg()) {
                            case 0:
                                ToastUtils.show("上传失败");
                                break;
                            case 1:
                                ToastUtils.show("上传成功");
                                finish();
                                break;
                            case -1:
                                ToastUtils.show("参数不能为空");
                                break;
                            default:
                                ToastUtils.show("上传失败");
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

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


}
