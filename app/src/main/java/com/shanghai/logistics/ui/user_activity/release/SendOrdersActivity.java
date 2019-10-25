package com.shanghai.logistics.ui.user_activity.release;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.services.ApiService;
import com.shanghai.logistics.models.services.UserService;
import com.shanghai.logistics.ui.user_activity.home_detail.VehicleLengthActivity;
import com.shanghai.logistics.util.FileUploadUtil;

import java.util.ArrayList;
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

/**
 * 用户派单
 *
 */
public class SendOrdersActivity extends SimpleActivity {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_handling_type)
    TextView mTvHandlingType;
    @BindView(R.id.tv_che_type)
    TextView mTvCheType;
    @BindView(R.id.tv_pay_type)
    TextView mTvPayType;
    @BindView(R.id.tv_startUserName)
    TextView mTvStartUserName;
    @BindView(R.id.tv_startPhone)
    TextView mTvStartPhone;
    @BindView(R.id.startAddress)
    TextView mTvStartAddress;
    @BindView(R.id.tv_endUserName)
    TextView mTvEndUserName;
//    @BindView(R.id.tv_startPhone)
//    TextView mTvEndPhone;
//    @BindView(R.id.startAddress)
//    TextView mTvEndAddress;

    List<String> mOptionsItems, mOptionsItems2;
    static final String TAG = SendOrdersActivity.class.getName();

    @Override
    protected int getLayout() {
        return R.layout.activity_send_orders;
    }

    @OnClick({R.id.img_back, R.id.tv_handling_type, R.id.tv_che_type, R.id.tv_pay_type, R.id.tv_send_orders})
    void onClicks(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_handling_type:
                //装货方式选择器
                OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        Log.i(TAG, "onOptionsSelect: " + mOptionsItems.get(options1));
                        mTvHandlingType.setText(mOptionsItems.get(options1));
                    }
                }).build();
                pvOptions.setPicker(mOptionsItems);
                pvOptions.show();
                break;
            case R.id.tv_che_type:
                //车长车型选择器
                Intent intent = new Intent(this, VehicleLengthActivity.class);
                intent.putExtra(Constants.ACTIVITY_TYPE, Constants.SEND_ORDERS);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_pay_type:
                //支付方式选择器
                OptionsPickerView pvOptions2 = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        Log.i(TAG, "onOptionsSelect: " + mOptionsItems2.get(options1));
                        mTvPayType.setText(mOptionsItems2.get(options1));
                    }
                }).build();
                pvOptions2.setPicker(mOptionsItems2);
                pvOptions2.show();
                break;
            case R.id.tv_send_orders:

                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mOptionsItems = new ArrayList<>();
        mOptionsItems.add("一装一卸");
        mOptionsItems.add("一装两卸");
        mOptionsItems.add("一装多卸");
        mOptionsItems.add("两装一卸");
        mOptionsItems.add("两装两卸");
        mOptionsItems.add("多装多卸");


        mOptionsItems2 = new ArrayList<>();
        mOptionsItems2.add("现付");
        mOptionsItems2.add("货到付款");
        mOptionsItems2.add("回付");
        mOptionsItems2.add("月结");
        mOptionsItems2.add("多笔付");


    }

    @SuppressLint("CheckResult")
    private void sendOrder() {
//        Map<String, RequestBody> map = new HashMap<>();
//        map.put("userAccount", FileUploadUtil.requestBody(mLoginPhone));
//        map.put("startUserName", FileUploadUtil.requestBody());
//        map.put("startPhone", FileUploadUtil.requestBody());
//        map.put("startAddress", FileUploadUtil.requestBody());
//        map.put("endUserName", FileUploadUtil.requestBody());
//        map.put("endPhone", FileUploadUtil.requestBody());
//        map.put("endAddress", FileUploadUtil.requestBody());
//        map.put("freight", FileUploadUtil.requestBody());
//        map.put("loadingTime", FileUploadUtil.requestBody());
//        map.put("loadMethod", FileUploadUtil.requestBody());
//        map.put("agentFee", FileUploadUtil.requestBody());
//        map.put("carModel", FileUploadUtil.requestBody());
//        map.put("payType", FileUploadUtil.requestBody());
//        map.put("payType", FileUploadUtil.requestBody());
//        map.put("driverDeposit", FileUploadUtil.requestBody());
//        map.put("receipt", FileUploadUtil.requestBody());
//        map.put("driverName", FileUploadUtil.requestBody());
//        map.put("driverPhone", FileUploadUtil.requestBody());
//        map.put("orderNo", FileUploadUtil.requestBody());
//        ApiService.getInstance()
//                .create(UserService.class, Constants.MAIN_URL)
//                .sendOrder()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new SingleObserver<ApiResponse<Integer>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        //  Log.i(TAG, "onSubscribe: ");
//                    }
//
//                    @Override
//                    public void onSuccess(ApiResponse<Integer> integer) {
//                        switch (integer.getMsg()) {
//                            case 0:
//                                ToastUtils.show("上传失败");
//                                break;
//                            case 1:
//                                ToastUtils.show("上传成功");
//                                break;
//                            case -1:
//                                ToastUtils.show("参数不能为空");
//                                break;
//                            case -2:
//                                ToastUtils.show("账号已经注册过");
//                                break;
//                        }
//                        Log.i(TAG, "onSuccess: " + integer.getMsg());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i(TAG, "onError: ");
//                    }
//
//                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                mTvCheType.setText(data.getStringExtra(Constants.VEHICLE_TYPE));
                Log.i("onActivityResult", "onActivityResult: " + data.getStringExtra("vehicleLength"));
                break;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
