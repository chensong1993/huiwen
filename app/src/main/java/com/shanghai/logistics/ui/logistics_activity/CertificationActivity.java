package com.shanghai.logistics.ui.logistics_activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.media.MediaSelector;
import com.example.media.bean.MediaSelectorFile;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.services.ApiService;
import com.shanghai.logistics.models.services.LogisticsService;
import com.shanghai.logistics.models.services.UserService;
import com.shanghai.logistics.util.DataUtil;
import com.shanghai.logistics.util.FileUploadUtil;
import com.shanghai.logistics.util.PhotoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


/*
 * 认证
 * */
public class CertificationActivity extends SimpleActivity implements EasyPermissions.PermissionCallbacks, BGASortableNinePhotoLayout.Delegate {

    private static final String TAG = "CertificationActivity";
    @BindView(R.id.snpl_moment_add_photos)
    BGASortableNinePhotoLayout mPhotosSnpl;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_companyName)
    EditText mEtCompanyName;
    @BindView(R.id.et_addressDetail)
    EditText mEtAddressDetail;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    Uri imageUri;
    File takePhotoDir;
    File file;
    List<File> fileList;
    @Override
    protected int getLayout() {
        return R.layout.activity_certification;
    }

    @Override
    protected void initEventAndData() {
        mPhotosSnpl.setDelegate(this);
        mTvTitle.setText("认证");
    }

    @OnClick({R.id.img_back, R.id.tv_confirm})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_confirm:
                uploadInfo();
                break;
        }
    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        Log.i("onClickAddNinePhotoItem", "onClickAddNinePhotoItem: ");
        choicePhotoWrapper();
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Log.i("onClickAddNinePhotoItem", "onClickDeleteNinePhotoItem: ");
        mPhotosSnpl.removeItem(position);
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Log.i("onClickAddNinePhotoItem", "onClickNinePhotoItem: ");
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(1) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        startActivityForResult(photoPickerPreviewIntent, RC_PHOTO_PREVIEW);
    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {

    }

    @AfterPermissionGranted(PRC_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            takePhotoDir = new File(Environment.getExternalStorageDirectory(),"takePhoto" );

            Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                    .cameraFileDir(takePhotoDir) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                    .maxChooseCount(1 - mPhotosSnpl.getItemCount()) // 图片选择张数的最大值
                    .selectedPhotos(null) // 当前已选中的图片路径集合
                    .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                    .build();
            startActivityForResult(photoPickerIntent, RC_CHOOSE_PHOTO);
        } else {
            EasyPermissions.requestPermissions(this, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", PRC_PHOTO_PICKER, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == PRC_PHOTO_PICKER) {
            Toast.makeText(this, "您拒绝了「图片选择」所需要的相关权限!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO) {
            //是否是多选
            // if (true) {

            List<String> stringList=BGAPhotoPickerActivity.getSelectedPhotos(data);
            Log.i("onActivityResult", "onActivityResult: " + stringList.get(0));
            mPhotosSnpl.setData(BGAPhotoPickerActivity.getSelectedPhotos(data));
          //  fileList.add(BGAPhotoPickerActivity.getSelectedPhotos(data));

            file = new File(stringList.get(0));

//            } else {
//                mPhotosSnpl.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));
//            }
        } else if (requestCode == RC_PHOTO_PREVIEW) {
            mPhotosSnpl.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
            List<String> stringList=BGAPhotoPickerActivity.getSelectedPhotos(data);
            file = new File(stringList.get(0));
        }
    }

    @SuppressLint("CheckResult")
    private void uploadInfo() {
        Map<String, RequestBody> map = new HashMap<>();
        File[] files = {file};
        String[] fileName = {"businessLicenseImg"};
     //   RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file);
     //   map.put("businessLicenseImg" + "\";filename=\"" + file.getName(), requestFile);

        map.put("phone", FileUploadUtil.requestBody("17621147941"));
        map.put("companyName", FileUploadUtil.requestBody(mEtCompanyName.getText().toString()));
        map.put("address", FileUploadUtil.requestBody(mEtAddress.getText().toString()));
        map.put("addressDetail", FileUploadUtil.requestBody(mEtAddressDetail.getText().toString()));
       // Log.i(TAG, "uploadInfo: " + takePhotoDir.getPath());
        ApiService.getInstance()
                .create(LogisticsService.class, Constants.MAIN_URL)
                .enterpriseCertification(FileUploadUtil.uploadInfo(fileName, map, files))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<ApiResponse<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: ");
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
                                ToastUtils.show("该企业已提交过认证");
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

