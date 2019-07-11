package com.shanghai.logistics.ui.user_activity.release;

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
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.VehicleLengthActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    List<String> mOptionsItems, mOptionsItems2;
    static final String TAG = SendOrdersActivity.class.getName();

    @Override
    protected int getLayout() {
        return R.layout.activity_send_orders;
    }

    @OnClick({R.id.img_back,R.id.tv_handling_type, R.id.tv_che_type, R.id.tv_pay_type})
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
                        mTvPayType.setText( mOptionsItems2.get(options1));
                    }
                }).build();
                pvOptions2.setPicker(mOptionsItems2);
                pvOptions2.show();
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
