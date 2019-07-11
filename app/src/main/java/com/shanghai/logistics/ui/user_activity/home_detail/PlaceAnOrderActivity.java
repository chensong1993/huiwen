package com.shanghai.logistics.ui.user_activity.home_detail;

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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.util.DataUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 下单
 *
 * */
public class PlaceAnOrderActivity extends SimpleActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
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
    TimePickerView pvTime;
    OptionsPickerView pvOptions;
    List<String> mOptionsItems;
    String payType;
    static final int ADDRESS = 2;
    static final int VEHICLE_TYPE = 1;

    @Override
    protected int getLayout() {
        return R.layout.activity_place_an_order;
    }

    @OnClick({R.id.img_back, R.id.tv_merchant_address, R.id.tv_user_address, R.id.tv_date, R.id.tv_che_type, R.id.tv_pay_type})
    void onClicks(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_merchant_address:
                startActivityForResult(new Intent(this, AddressActivity.class), 2);
                break;
            case R.id.tv_user_address:
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
                Intent intent = new Intent(this, VehicleLengthActivity.class);
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
            int activityType = bundle.getInt(Constants.ACTIVITY_TYPE);
            switch (activityType) {
                case Constants.BILLING_ACTIVITY:
                    mTvTitle.setText("开单");
                    mTvPayType.setText("现付");
                    mTvCheType.setText("大货车/2.1");
                    mEtName.setText("name");
                    mEtPhone.setText("123465789");
                    mEtAddress.setText("河南");
                    break;
                default:
                    mTvTitle.setText("下单");
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case VEHICLE_TYPE:
                mTvCheType.setText(data.getStringExtra(Constants.VEHICLE_TYPE));
                //  Log.i("onActivityResult", "onActivityResult: " + data.getStringExtra("vehicleLength"));
                break;
            case ADDRESS:
                Bundle bundle = data.getBundleExtra("addressActivity");
                String name = bundle.getString("address_name");
                String phone = bundle.getString("address_phone");
                String address = bundle.getString("address_address");
                mEtName.setText(name);
                mEtPhone.setText(phone);
                mEtAddress.setText(address);
                Log.i("onActivityResult", "onActivityResult: " + name);
                break;
        }
//        if (resultCode == Activity.RESULT_OK) {
//
//            Log.i("onActivityResult", "onActivityResult: " + data.getStringExtra("vehicleLength"));
//        }
//        if ()


        super.onActivityResult(requestCode, resultCode, data);
    }

}
