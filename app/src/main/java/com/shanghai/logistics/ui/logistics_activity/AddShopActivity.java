package com.shanghai.logistics.ui.logistics_activity;

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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.media.MediaSelector;
import com.hjq.toast.ToastUtils;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.style.cityjd.JDCityConfig;
import com.lljjcoder.style.cityjd.JDCityPicker;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.http.Uploading;
import com.shanghai.logistics.models.services.ApiService;
import com.shanghai.logistics.models.services.LogisticsService;
import com.shanghai.logistics.util.DataUtil;
import com.shanghai.logistics.util.FileUploadUtil;
import com.shanghai.logistics.util.PhotoUtils;
import com.shanghai.logistics.util.SystemUtil;
import com.shanghai.logistics.widget.BasePopup.ImagePopup;

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
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 新增商品
 */
public class AddShopActivity extends SimpleActivity implements AMapLocationListener, EasyPermissions.PermissionCallbacks, BGASortableNinePhotoLayout.Delegate {

    private static final String TAG = "AddShopActivity";
    @BindView(R.id.snpl_moment_add_photos)
    BGASortableNinePhotoLayout mPhotosSnpl;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_shop_name)
    EditText mEtShopName;
    @BindView(R.id.et_introduction)
    EditText mEtIntroduction;
    @BindView(R.id.et_weight)
    EditText mEtWeight;
    @BindView(R.id.et_volume)
    EditText mEtVolume;
    @BindView(R.id.et_aging)
    EditText mEtAging;
    @BindView(R.id.tv_startAddress)
    TextView mTvStartAddress;
    @BindView(R.id.tv_endAddress)
    TextView mTvEndAddress;
    @BindView(R.id.sw_aging)
    Switch mSwAging;
    @BindView(R.id.sw_isBusiness)
    Switch mSwIsBusiness;
    @BindView(R.id.sw_isPromotion)
    Switch mSwIsPromotion;
    @BindView(R.id.img_main)
    ImageView mImgMain;
    @BindView(R.id.img_detail)
    ImageView mImgDetail;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.et_jiedan)
    EditText mEtReceiverId;
    MediaSelector.MediaOptions mediaOptions;
    ImagePopup imagePopup;
    File MainFile, DetailFile;
    List<String> ImgFileList;
    Uri imageUri;
    int typeFile;
    int Aging, Business, Promotion;
    JDCityPicker cityPicker;
    int storeId;
    //String phoneId;
    JDCityConfig jdCityConfig;

    Double latitude, longitude;

    //声明mlocationClient对象
    public AMapLocationClient mLocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_shop;
    }

    @OnClick({R.id.img_back, R.id.tv_startAddress, R.id.tv_endAddress, R.id.tv_confirm, R.id.tv_address, R.id.ll_order, R.id.ll_phone})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_main:
                ImgUpload(0);
                break;
            case R.id.tv_startAddress:
                SystemUtil.HideKeyboard(mTvAddress);
                jdCityConfig = new JDCityConfig.Builder().build();
                jdCityConfig.setShowType(JDCityConfig.ShowType.PRO_CITY);
                cityPicker.setConfig(jdCityConfig);
                cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        if (city.getName().equals("省直辖县级行政单位")) {
                            mTvStartAddress.setText(province.getName());
                        } else {
                            mTvStartAddress.setText(city.getName());
                        }

                    }

                    @Override
                    public void onCancel() {
                    }
                });
                cityPicker.showCityPicker();
                break;
            case R.id.tv_endAddress:
                SystemUtil.HideKeyboard(mTvAddress);
                jdCityConfig = new JDCityConfig.Builder().build();
                jdCityConfig.setShowType(JDCityConfig.ShowType.PRO_CITY);
                cityPicker.setConfig(jdCityConfig);
                cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        if (city.getName().equals("省直辖县级行政单位")) {
                            mTvEndAddress.setText(province.getName());
                        } else {
                            mTvEndAddress.setText(city.getName());
                        }

                    }

                    @Override
                    public void onCancel() {
                    }
                });
                cityPicker.showCityPicker();
                break;
            case R.id.img_detail:
                ImgUpload(1);
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.ll_order:
                startActivity(new Intent(this, OrderActivity.class));
                break;
            case R.id.ll_phone:
                startActivityForResult(new Intent(this, AddPhoneActivity.class), 0);
                //    startActivity(new Intent(this, PhoneListActivity.class));

                break;
            case R.id.tv_confirm:
                if (ImgFileList == null) {
                    ToastUtils.show("请上传商品照片");
                    return;
                }
                if (ImgFileList.size() < 2) {
                    ToastUtils.show("请上传两张商品照片");
                    return;
                }
                if (mEtShopName.getText().toString().isEmpty()) {
                    ToastUtils.show("商品名称不能为空");
                    return;
                }
                if (mEtIntroduction.getText().toString().isEmpty()) {
                    ToastUtils.show("商品简介不能为空");
                    return;
                }
                if (mEtVolume.getText().toString().isEmpty()) {
                    ToastUtils.show("重量不能为空");
                    return;
                }
                if (mEtWeight.getText().toString().isEmpty()) {
                    ToastUtils.show("体积不能为空");
                    return;
                }
                if (mTvAddress.getText().toString().isEmpty()) {
                    ToastUtils.show("位置不能为空");
                    return;
                }
                if (mEtShopName.getText().toString().isEmpty()) {
                    ToastUtils.show("商品名称不能为空");
                    return;
                }
                if (mEtAging.getText().toString().isEmpty()) {
                    ToastUtils.show("时效不能为空");
                    return;
                }
                if (mTvStartAddress.getText().toString().isEmpty()) {
                    ToastUtils.show("出发城市不能为空");
                    return;
                }
                if (mTvEndAddress.getText().toString().isEmpty()) {
                    ToastUtils.show("达到城市不能为空");
                    return;
                }
                if (mEtReceiverId.getText().toString().isEmpty()) {
                    ToastUtils.show("接单人不能为空");
                    return;
                }
//                if (phoneId == null) {
//                    ToastUtils.show("手机号不能为空");
//                    return;
//                }

                uploadInfo();
                break;
            case R.id.tv_address:
                SystemUtil.HideKeyboard(mTvAddress);
                jdCityConfig = new JDCityConfig.Builder().build();
                jdCityConfig.setShowType(JDCityConfig.ShowType.PRO_CITY);
                cityPicker.setConfig(jdCityConfig);
                cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        if (city.getName().equals("省直辖县级行政单位")) {
                            mTvAddress.setText(province.getName());
                        } else {
                            mTvAddress.setText(province.getName() + "-" + city.getName());
                        }

                    }

                    @Override
                    public void onCancel() {
                    }
                });
                cityPicker.showCityPicker();
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("新增商品");
        mPhotosSnpl.setDelegate((BGASortableNinePhotoLayout.Delegate) this);
        storeId = getIntent().getIntExtra(Constants.STORE_ID, 0);
        Log.i(TAG, "storeId: " + storeId);
        cityPicker = new JDCityPicker();
        cityPicker.init(this);
        onPosition();
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
                        MediaSelector.with(AddShopActivity.this).setMediaOptions(mediaOptions).openMediaActivity();
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
                        MainFile = new File(Environment.getExternalStorageDirectory().getPath() + "/+" + time + ".jpg");
                        imageUri = Uri.fromFile(MainFile);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, MainFile);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
                        break;
                    case 1:
                        DetailFile = new File(Environment.getExternalStorageDirectory().getPath() + "/+" + time + ".jpg");
                        imageUri = Uri.fromFile(DetailFile);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, DetailFile);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
                        break;
                }

            } else {
                ToastUtils.show("设备没有SD卡！");
            }
        }
    }


    @SuppressLint("CheckResult")
    private void uploadInfo() {
        if (longitude == null) {
            longitude = 0.0;
        }
        if (latitude == null) {
            latitude = 0.0;
        }
        if (mSwAging.isChecked()) {
            Aging = 1;
        } else {
            Aging = 0;
        }
        if (mSwIsBusiness.isChecked()) {
            Business = 1;
        } else {
            Business = 0;
        }
        if (mSwIsPromotion.isChecked()) {
            Promotion = 1;
        } else {
            Promotion = 0;
        }
        Uploading.AddShop(ImgFileList, mLoginPhone, mEtReceiverId.getText().toString(), mEtShopName.getText().toString(), mEtShopName.getText().toString(),
                storeId, mEtIntroduction.getText().toString(), mEtWeight.getText().toString(), mEtVolume.getText().toString(),
                mEtAging.getText().toString(), mTvStartAddress.getText().toString(), mTvEndAddress.getText().toString(), Aging,
                Business, Promotion, longitude, latitude, mTvAddress.getText().toString(), new SingleObserver<ApiResponse<Integer>>() {
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

                        }
                        Log.i(TAG, "onSuccess: " + integer.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage());
                        ToastUtils.show("上传失败");
                    }
                });
    }

    void onPosition() {

        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mLocationClient.setLocationListener(this);

        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                latitude = amapLocation.getLatitude();//获取纬度
                longitude = amapLocation.getLongitude();//获取经度
                float longitud = amapLocation.getAccuracy();//获取精度信息

                //Log.i(TAG, "onLocationChanged: " + amapLocation.getAccuracy()+"   "+amapLocation.getLatitude()+amapLocation.getLongitude());
                //    AddressName = amapLocation.getAddress();

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        choicePhotoWrapper();
        ImgFileList = models;
        Log.i("onActivityResult", "onActivityResult:3 " + models.size());
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        mPhotosSnpl.removeItem(position);
        ImgFileList = models;
        Log.i("onActivityResult", "onActivityResult:3 " + models.size());
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Log.i("onClickAddNinePhotoItem", "onClickNinePhotoItem: ");
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(2) // 图片选择张数的最大值
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
            DetailFile = new File(Environment.getExternalStorageDirectory(), "takePhoto");

            Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                    .cameraFileDir(DetailFile) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                    .maxChooseCount(2 - mPhotosSnpl.getItemCount()) // 图片选择张数的最大值
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

//        if (resultCode == AddPhoneActivity.VEHICLE_TYPE) {
//            phoneId = data.getStringExtra(Constants.PHONE_ID);
//        }
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO) {
            if (mPhotosSnpl.getItemCount() < 2) {
                mPhotosSnpl.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));
                ImgFileList = mPhotosSnpl.getData();
                Log.i("onActivityResult", "onActivityResult: " + ImgFileList.size());
            }

            //是否是多选
//            if (true) {
//                ImgFileList = BGAPhotoPickerActivity.getSelectedPhotos(data);
//                Log.i("onActivityResult", "onActivityResult: " + ImgFileList.size());
//                mPhotosSnpl.setData(BGAPhotoPickerActivity.getSelectedPhotos(data));
//                //ImgFileList.addAll(BGAPhotoPickerActivity.getSelectedPhotos(data));
//
//            } else {
//                ImgFileList = BGAPhotoPickerActivity.getSelectedPhotos(data);
//                Log.i("onActivityResult", "onActivityResult:2 " + ImgFileList.size());
//                mPhotosSnpl.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));
//           }

        } else if (requestCode == RC_PHOTO_PREVIEW) {
            //   Log.i("onActivityResult", "onActivityResult:3 " + ImgFileList.size());
            mPhotosSnpl.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
            List<String> stringList = BGAPhotoPickerActivity.getSelectedPhotos(data);
            MainFile = new File(stringList.get(0));
        }
    }

//    /**
//     * 响应权限
//     *
//     * @param requestCode
//     * @param permissions
//     * @param grantResults
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode) {
//            case Constants.CAMERA_PERMISSIONS_REQUEST_CODE: //调用系统相机申请拍照权限回调
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    if (PhotoUtils.hasSdcard()) {
//                        String time = DataUtil.dateToStr(new Date());
//                        switch (typeFile) {
//                            case 0:
//                                MainFile = new File(Environment.getExternalStorageDirectory().getPath() + "/+" + time + ".jpg");
//                                imageUri = Uri.fromFile(MainFile);
//                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//                                    imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, MainFile);//通过FileProvider创建一个content类型的Uri
//                                PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
//                                break;
//                            case 1:
//                                DetailFile = new File(Environment.getExternalStorageDirectory().getPath() + "/+" + time + ".jpg");
//                                imageUri = Uri.fromFile(DetailFile);
//                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//                                    imageUri = FileProvider.getUriForFile(this, Constants.FILE_PROVIDER, DetailFile);//通过FileProvider创建一个content类型的Uri
//                                PhotoUtils.takePicture(this, imageUri, Constants.CODE_CAMERA_REQUEST);
//                                break;
//                        }
//
//                    } else {
//                        ToastUtils.show("设备没有SD卡！");
//                    }
//                } else {
//                    ToastUtils.show("请允许打开相机！");
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.CAMERA_PERMISSIONS_REQUEST_CODE);
//                    }
//                }
//                break;
//
//        }
//    }

//    /**
//     * 选择图片结果回调
//     *
//     * @param requestCode
//     * @param resultCode
//     * @param data
//     */
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //从相册获取
//        if (resultCode == Contast.CODE_RESULT_MEDIA && requestCode == Contast.CODE_REQUEST_MEDIA) {
//            List<MediaSelectorFile> mediaList = MediaSelector.resultMediaFile(data);
//            switch (typeFile) {
//                case 0:
//                    for (int i = 0; i < mediaList.size(); i++) {
//                        MainFile = new File(mediaList.get(i).filePath);
//                        Glide.with(this).load(mediaList.get(i).filePath).into(mImgMain);
//                        Log.i(TAG, "onActivityResult: " + MainFile.getAbsolutePath());
//
//                    }
//                    break;
//                case 1:
//                    for (int i = 0; i < mediaList.size(); i++) {
//                        DetailFile = new File(mediaList.get(i).filePath);
//                        Glide.with(this).load(mediaList.get(i).filePath).into(mImgDetail);
//                        Log.i(TAG, "onActivityResult: " + DetailFile.getAbsolutePath());
//
//                    }
//                    break;
//            }
//
//        }
//        if (requestCode == Constants.CODE_CAMERA_REQUEST) {
//            switch (typeFile) {
//                case 0:
//                    if (MainFile != null) {
//                        Log.i(TAG, "onActivityResult: " + MainFile);
//                        Glide.with(this).load(MainFile).into(mImgMain);
//                    } else {
//                        ToastUtils.show("获取照片失败");
//                    }
//                    break;
//                case 1:
//                    if (DetailFile != null) {
//                        Log.i(TAG, "onActivityResult: " + DetailFile);
//                        Glide.with(this).load(DetailFile).into(mImgDetail);
//                    } else {
//                        ToastUtils.show("获取照片失败");
//                    }
//                    break;
//
//            }
//
//        }
//
//    }
}
