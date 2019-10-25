package com.shanghai.logistics.ui.logistics_activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.http.Uploading;
import com.shanghai.logistics.models.services.ApiService;
import com.shanghai.logistics.models.services.LogisticsService;
import com.shanghai.logistics.models.services.UserService;
import com.shanghai.logistics.ui.activity.GaodeActivity;
import com.shanghai.logistics.util.FileUploadUtil;
import com.shanghai.logistics.util.SystemUtil;

import java.io.File;
import java.util.ArrayList;
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
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/*
 * 新增商铺
 * */
public class AddStoresActivity extends SimpleActivity implements EasyPermissions.PermissionCallbacks, BGASortableNinePhotoLayout.Delegate {
    private static final int PRC_PHOTO_PICKER = 1;
    private static final int RC_CHOOSE_PHOTO = 1;
    private static final int RC_PHOTO_PREVIEW = 2;
    private static final String TAG = "AddStoresActivity";
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_baidu)
    TextView mTvAddress;
    @BindView(R.id.ll_main)
    LinearLayout mLlMain;
    @BindView(R.id.et_storeName)
    EditText mEtStoreName;
    @BindView(R.id.et_contactName)
    EditText mEtContactName;
    @BindView(R.id.et_contactPhone)
    EditText mEtContactPhone;
    @BindView(R.id.et_position)
    EditText mEtPosition;
    @BindView(R.id.et_jieshao)
    EditText mEtJieShao;
    Double longitude, latitude;
    @BindView(R.id.snpl_moment_add_photos)
    BGASortableNinePhotoLayout mPhotosSnpl;
    Bundle bundle;
    List<String> FilePathList;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_stores;
    }

    @Override
    protected void initEventAndData() {
        mPhotosSnpl.setDelegate(this);
        mTvTitle.setText("门店信息");
        bundle = new Bundle();
        bundle = getIntent().getBundleExtra(Constants.ACTIVITY_TYPE);
        if (bundle != null) {
            bundle.getInt(Constants.ACTIVITY_TYPE, Constants.LOGISTICS_ME_FRAGMENT);
        }
    }

    @OnClick({R.id.img_back, R.id.tv_baidu, R.id.tv_confirm})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                if (mEtStoreName.getText().toString().isEmpty()) {
                    ToastUtils.show("门店名称不能为空");
                    return;
                }
                if (mEtStoreName.getText().toString().isEmpty()) {
                    ToastUtils.show("联系人不能为空");
                    return;
                }
                if (mEtStoreName.getText().toString().isEmpty()) {
                    ToastUtils.show("联系电话不能为空");
                    return;
                }
                if (mEtStoreName.getText().toString().isEmpty()) {
                    ToastUtils.show("门店地址不能为空");
                    return;
                }
                if (mEtStoreName.getText().toString().isEmpty()) {
                    ToastUtils.show("门店名称不能为空");
                    return;
                }


                uploadInfo();
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_baidu:
                Intent intent = new Intent(this, GaodeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.ACTIVITY_TYPE, Constants.ADD_STORES_ACTIVITY);
                intent.putExtra(Constants.ACTIVITY_TYPE, bundle);
                startActivityForResult(intent, Constants.REQUEST_CODE_SCAN);
                break;
        }
    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        choicePhotoWrapper();
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        mPhotosSnpl.removeItem(position);
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(mPhotosSnpl.getMaxItemCount()) // 图片选择张数的最大值
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
            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");

            Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                    .cameraFileDir(null) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                    .maxChooseCount(5 - mPhotosSnpl.getItemCount()) // 图片选择张数的最大值
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

        if (requestCode == Constants.REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            Log.i(TAG, "onActivityResult: zuobiao");
            Bundle bundle = data.getBundleExtra(Constants.ADDRESS_NAME);
            if (bundle != null) {
                mTvAddress.setText(bundle.getString(Constants.ADDRESS_NAME));
                latitude = bundle.getDouble(Constants.LATITUDE);
                longitude = bundle.getDouble(Constants.LONGITUDE);
            } else {
                ToastUtils.show("详细地址未获取到");
            }
        }

        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO) {
            //是否是多选
//            if (false) {
//                mPhotosSnpl.setData(BGAPhotoPickerActivity.getSelectedPhotos(data));
//            } else {
            mPhotosSnpl.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));
            FilePathList = new ArrayList<>();
            FilePathList = BGAPhotoPickerActivity.getSelectedPhotos(data);
            Log.i(TAG, "onActivityResult: 相册");
            //      }
        } else if (requestCode == RC_PHOTO_PREVIEW) {
            mPhotosSnpl.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
        }
    }

    @SuppressLint("CheckResult")
    private void uploadInfo() {
        Uploading.AddStores(FilePathList, mLoginPhone, mEtStoreName.getText().toString(),
                mEtContactPhone.getText().toString(), mTvAddress.getText().toString(), mEtPosition.getText().toString(),
                longitude + "", latitude + "", mEtJieShao.getText().toString(), mEtContactName.getText().toString(), new SingleObserver<ApiResponse<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ApiResponse<Integer> i) {
                        switch (i.getMsg()) {
                            case 0:
                                ToastUtils.show("上传失败");
                                break;
                            case 1:
                                finish();
                                break;
                            case -1:
                                ToastUtils.show("参数不能为空");
                                break;
                            case -2:
                                ToastUtils.show("账号已经注册过");
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                    }

                });

    }

}
