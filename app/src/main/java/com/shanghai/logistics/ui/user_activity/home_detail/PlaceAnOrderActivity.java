package com.shanghai.logistics.ui.user_activity.home_detail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.base.connectors.logistics.LogisticsOrderDetailConnector;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.logistics.LOrderEntity;
import com.shanghai.logistics.models.entity.rxbus.BillingEvent;
import com.shanghai.logistics.models.http.Uploading;
import com.shanghai.logistics.models.services.ApiService;
import com.shanghai.logistics.models.services.LogisticsService;
import com.shanghai.logistics.models.services.UserService;
import com.shanghai.logistics.presenters.logistics.LogisticsOrderDetailPresenter;
import com.shanghai.logistics.util.DataUtil;
import com.shanghai.logistics.util.FileUploadUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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
import okhttp3.RequestBody;

/*
 * 下单
 *
 * */
public class PlaceAnOrderActivity extends BaseActivity<LogisticsOrderDetailPresenter> implements LogisticsOrderDetailConnector.View {

    private static final String TAG = "PlaceAnOrderActivity";
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_end_name)
    EditText mEtEndName;
    @BindView(R.id.et_end_phone)
    EditText mEtEndPhone;
    @BindView(R.id.et_end_address)
    EditText mEtEndAddress;
    @BindView(R.id.et_goods)
    EditText mEtGoods;
    @BindView(R.id.et_volume)
    EditText mEtVolume;
    @BindView(R.id.et_weight)
    EditText mEtWeight;
    @BindView(R.id.et_pieces)
    EditText mEtPieces;
    @BindView(R.id.et_receipt)
    EditText mEtReceipt;
    @BindView(R.id.et_remarks)
    EditText mEtRemarks;
    @BindView(R.id.et_estimatedCost)
    EditText mEtEstimatedCost;
    @BindView(R.id.rb_go)
    RadioButton mRbGo;
    @BindView(R.id.rb_home)
    RadioButton mRbHome;

    @BindView(R.id.rb_end_go)
    RadioButton mRbEndGo;
    @BindView(R.id.rb_end_home)
    RadioButton mRbEndHome;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.tv_che_type)
    TextView mTvCheType;
    @BindView(R.id.tv_pay_type)
    TextView mTvPayType;
    @BindView(R.id.tv_merchant_address)
    TextView mTvMerchantAddress;
    @BindView(R.id.tv_user_address)
    TextView mTvUserAddress;
    @BindView(R.id.ed_name)
    EditText mEtName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.tv_order_num)
    TextView mTvOrderNum;
    @BindView(R.id.img_start)
    ImageView mImgStart;
    @BindView(R.id.img_end)
    ImageView mImgEnd;
    TimePickerView pvTime;
    OptionsPickerView pvOptions;
    List<String> mOptionsItems;
    String payType;
    public static final int ADDRESS = 2;
    public static final int END_ADDRESS = 3;
    public static final int VEHICLE_TYPE = 1;
    LOrderEntity lOrderEntity;
    Bundle bundle;
    Intent intent;
    String name, phone, address;
    int startType, endType;
    int activityType;
    int storeId;
    int dedicatedLine;
    String storeAccount;

    @Override
    protected int getLayout() {
        return R.layout.activity_place_an_order;
    }

    @OnClick({R.id.rb_home, R.id.rb_end_home, R.id.rb_go, R.id.rb_end_go, R.id.tv_submit, R.id.img_back, R.id.tv_merchant_address, R.id.tv_user_address, R.id.tv_date, R.id.tv_che_type, R.id.tv_pay_type})
    void onClicks(View v) {
        switch (v.getId()) {
//            case R.id.rb_go:
//                if (mRbGo.isChecked()) {
//                    startType = 1;
//                }
//                break;
//            case R.id.rb_end_go:
//                if (mRbEndGo.isChecked()) {
//                    startType = 2;
//                }
//                break;
//            case R.id.rb_home:
//                if (mRbHome.isChecked()) {
//                    endType = 1;
//                }
//                break;
//            case R.id.rb_end_home:
//                if (mRbEndHome.isChecked()) {
//                    endType = 2;
//                }
//                break;
            case R.id.tv_submit:
                if (mEtName.getText().toString().isEmpty()) {
                    ToastUtils.show("姓名不能为空");
                    return;
                }
                if (mEtPhone.getText().toString().isEmpty()) {
                    ToastUtils.show("手机号不能为空");
                    return;
                }
                if (mEtAddress.getText().toString().isEmpty()) {
                    ToastUtils.show("发货地址不能为空");
                    return;
                }
                if (mEtEndName.getText().toString().isEmpty()) {
                    ToastUtils.show("收货人不能为空");
                    return;
                }
                if (mEtEndPhone.getText().toString().isEmpty()) {
                    ToastUtils.show("收货人手机号不能为空");
                    return;
                }
                if (mEtEndAddress.getText().toString().isEmpty()) {
                    ToastUtils.show("收货人地址不能为空");
                    return;
                }
                if (mEtGoods.getText().toString().isEmpty()) {
                    ToastUtils.show("货物不能为空");
                    return;
                }
                if (mEtVolume.getText().toString().isEmpty()) {
                    ToastUtils.show("体积不能为空");
                    return;
                }
                if (mEtWeight.getText().toString().isEmpty()) {
                    ToastUtils.show("重量不能为空");
                    return;
                }
                if (mEtPieces.getText().toString().isEmpty()) {
                    ToastUtils.show("件数不能为空");
                    return;
                }
                if (mTvDate.getText().toString().isEmpty()) {
                    ToastUtils.show("装货时间不能为空");
                    return;
                }
                if (mTvCheType.getText().toString().isEmpty()) {
                    ToastUtils.show("车长车型不能为空");
                    return;
                }
                if (mTvPayType.getText().toString().isEmpty()) {
                    ToastUtils.show("支付方式不能为空");
                    return;
                }
                if (mEtReceipt.getText().toString().isEmpty()) {
                    ToastUtils.show("回单不能为空");
                    return;
                }
                if (mEtEstimatedCost.getText().toString().isEmpty()) {
                    ToastUtils.show("预估费用不能为空");
                    return;
                }
                Log.i(TAG, "onClicks: ");
                switch (activityType) {
                    case Constants.BILLING_ACTIVITY:
                        uploadInfo();
                        break;
                    case Constants.HOME_DETAIL_ACTIVITY:
                        UserUploadInfo();
                        break;
                    default:

                        break;
                }
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_merchant_address:
                intent = new Intent(this, AddressActivity.class);
                bundle = new Bundle();
                bundle.putInt(Constants.START_TYPE, ADDRESS);
                intent.putExtra(Constants.All_VALUE, bundle);
                startActivityForResult(intent, ADDRESS);
                break;
            case R.id.tv_user_address:
                intent = new Intent(this, AddressActivity.class);
                bundle = new Bundle();
                bundle.putInt(Constants.START_TYPE, END_ADDRESS);
                intent.putExtra(Constants.All_VALUE, bundle);
                startActivityForResult(intent, END_ADDRESS);
                break;
            case R.id.tv_date:
                pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTvDate.setText(DataUtil.DateToString(date, DataUtil.DATE_TO_STRING_SHORT_PATTERN));
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.tv_che_type:
                // startActivityForResult(new Intent(this, VehicleTypeActivity.class),1);
                intent = new Intent(this, VehicleLengthActivity.class);
                intent.putExtra(Constants.ACTIVITY_TYPE, Constants.PlaceAnOrder);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_pay_type:
                pvOptions.show();
                break;

        }
    }


    @Override
    protected void initEventAndData() {

        Bundle bundle = getIntent().getBundleExtra(Constants.ACTIVITY_TYPE);
        if (bundle != null) {
            activityType = bundle.getInt(Constants.ACTIVITY_TYPE);
            storeId = bundle.getInt(Constants.STORE_ID);
            dedicatedLine = bundle.getInt(Constants.DEDICATED_LINE_PHONE_ID);
            storeAccount = bundle.getString(Constants.STORE_ACCOUNT);

            switch (activityType) {
                case Constants.BILLING_ACTIVITY:
                    mTvTitle.setText("开单");
                    lOrderEntity = (LOrderEntity) bundle.getSerializable(Constants.BILLING_VALUE);
                    mPresenter.getLogisticsOrderDetail(lOrderEntity.getOrderNo());
                    mTvOrderNum.setText(lOrderEntity.getOrderNo());
                    mEtGoods.setText(lOrderEntity.getGoods());
                    mEtVolume.setText(lOrderEntity.getVolume() + "");
                    mEtWeight.setText(lOrderEntity.getWeight() + "");
                    mEtPieces.setText(lOrderEntity.getPieces() + "");
                    Log.i(TAG, "initEventAndData: " + lOrderEntity.toString());
//                    mTvPayType.setText("现付");
//                    mTvCheType.setText("大货车/2.1");
//                    mEtName.setText("name");
//                    mEtPhone.setText("123465789");
//                    mEtAddress.setText("河南");
                    break;
                case Constants.HOME_DETAIL_ACTIVITY:
                    mTvOrderNum.setVisibility(View.GONE);
                    break;
                default:

                    break;
            }
        }

        //选择器
        mOptionsItems = new ArrayList<>();
        mOptionsItems.add("现付");
        mOptionsItems.add("货到付款");
        mOptionsItems.add("回付");
        mOptionsItems.add("月结");
        mOptionsItems.add("多笔付");
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                mTvPayType.setText(mOptionsItems.get(options1));
            }
        }).build();
        pvOptions.setPicker(mOptionsItems);

    }

    @Override
    public void LogisticsOrderDetail(LOrderEntity e) {
        Glide.with(this).load(Constants.MAIN_URL + e.getHeadImgUrl()).into(mImgStart);
        Glide.with(this).load(Constants.MAIN_URL + e.getHeadImgUrl()).into(mImgEnd);
        mTvDate.setText(e.getCreateTime());
        mEtName.setText(e.getStartUserName());
        mEtPhone.setText(e.getStartPhone());
        mEtAddress.setText(e.getStartAddress());
        if (e.getStartDeliveryType() == 1) {
            mRbGo.setChecked(true);
        } else {
            mRbHome.setChecked(true);
        }
        if (e.getEndDeliveryType() == 1) {
            mRbEndGo.setChecked(true);
        } else {
            mRbEndHome.setChecked(true);
        }
        mEtEndName.setText(e.getEndUserName());
        mEtEndPhone.setText(e.getEndPhone());
        mEtEndAddress.setText(e.getEndAddress());
        mTvCheType.setText(e.getCarModel());
        mTvPayType.setText(e.getPayType());
        mEtReceipt.setText(e.getReceipt());
        mEtEstimatedCost.setText(e.getEstimatedCost() + "");
        mEtRemarks.setText(e.getRemark());
    }

    @Override
    public void LogisticsOrderDetailErr(String s) {
        Log.i(TAG, "LogisticsOrderDetailErr: " + s);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode > 1) {
            bundle = data.getBundleExtra("addressActivity");
            name = bundle.getString("address_name");
            phone = bundle.getString("address_phone");
            address = bundle.getString("address_address");
        }

        switch (resultCode) {
            case VEHICLE_TYPE:
                mTvCheType.setText(data.getStringExtra(Constants.VEHICLE_TYPE));
                //  Log.i("onActivityResult", "onActivityResult: " + data.getStringExtra("vehicleLength"));
                break;
            case ADDRESS:
                mEtName.setText(name);
                mEtPhone.setText(phone);
                mEtAddress.setText(address);
                Log.i("onActivityResult", "onActivityResult: " + name);
                break;
            case END_ADDRESS:
                mEtEndName.setText(name);
                mEtEndPhone.setText(phone);
                mEtEndAddress.setText(address);
                Log.i("onActivityResult", "onActivityResult: " + name);
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    //物流端开单修改
    private void uploadInfo() {
        if (mRbGo.isChecked()) {
            startType = 1;
        } else {
            startType = 2;
        }

        if (mRbEndGo.isChecked()) {
            endType = 1;
        } else {
            endType = 2;
        }

        Uploading.LPlaceAnOrder(mTvOrderNum.getText().toString(), startType, mEtName.getText().toString(), mEtPhone.getText().toString(),
                mEtAddress.getText().toString(), endType, mEtEndName.getText().toString(), mEtEndPhone.getText().toString(),
                mEtEndAddress.getText().toString(), mEtGoods.getText().toString(), mEtVolume.getText().toString(), mEtWeight.getText().toString(),
                mEtPieces.getText().toString(), mTvDate.getText().toString(), mTvCheType.getText().toString(), mTvPayType.getText().toString(),
                mEtReceipt.getText().toString(), mEtEstimatedCost.getText().toString(), new SingleObserver<ApiResponse<Integer>>() {
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
                             //   EventBus.getDefault().post(new BillingEvent(1));
                                finish();
                                break;
                            case -1:
                                ToastUtils.show("参数不能为空");
                                break;
                            default:
                                ToastUtils.show("提交失败" + integer.getMsg());
                                break;

                        }
                        Log.i(TAG, "onSuccess: " + integer.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //ToastUtils.show("提交失败" + e.getMessage());
                        Log.i(TAG, "onError: " + e.getMessage());
                    }
                }
        );
    }


    //用户端下单
    private void UserUploadInfo() {
        if (mRbGo.isChecked()) {
            startType = 1;
        } else {
            startType = 2;
        }

        if (mRbEndGo.isChecked()) {
            endType = 1;
        } else {
            endType = 2;
        }

        Uploading.UserPlaceAnOrder(mLoginPhone, mLoginPhone, storeId, 1, dedicatedLine, startType,
                mEtName.getText().toString(), mEtPhone.getText().toString(), mEtAddress.getText().toString(),
                endType, mEtEndName.getText().toString(), mEtEndPhone.getText().toString(), mEtEndAddress.getText().toString(),
                mEtGoods.getText().toString(), mEtVolume.getText().toString(), mEtWeight.getText().toString(),
                mEtPieces.getText().toString(), mTvDate.getText().toString(), mTvCheType.getText().toString(),
                mTvPayType.getText().toString(), mEtReceipt.getText().toString(), mEtRemarks.getText().toString(),
                mEtEstimatedCost.getText().toString(), 1, new SingleObserver<ApiResponse<Integer>>() {
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
                                ToastUtils.show("提交失败" + integer.getMsg());
                                break;

                        }
                        Log.i(TAG, "onSuccess: " + integer.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                          ToastUtils.show("提交失败" + e.getMessage());
                        Log.i(TAG, "onError: " + e.getMessage());
                    }
                });

    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


}
