package com.shanghai.logistics.ui.logistics_activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.services.ApiService;
import com.shanghai.logistics.models.services.LogisticsService;
import com.shanghai.logistics.ui.user_activity.home_detail.PlaceAnOrderActivity;
import com.shanghai.logistics.util.FileUploadUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class AddPhoneActivity extends SimpleActivity {
    private static final String TAG = "AddPhoneActivity";
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_ps)
    EditText mEtPs;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    static final int VEHICLE_TYPE = 1;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_phone;
    }

    @OnClick({R.id.et_ps, R.id.img_back, R.id.et_phone, R.id.tv_save})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_save:
                uploadInfo();
                break;

        }
    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("添加号码");
    }

    @SuppressLint("CheckResult")
    private void uploadInfo() {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("phone", FileUploadUtil.requestBody(mEtPhone.getText().toString()));
        map.put("name", FileUploadUtil.requestBody(mEtPs.getText().toString()));
        ApiService.getInstance()
                .create(LogisticsService.class, Constants.MAIN_URL)
                .dedicatedLinePhone(map)
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
                                ToastUtils.show("添加失败");
                                break;
                            case 1:
                                Intent intent = new Intent(AddPhoneActivity.this, AddShopActivity.class);
                                intent.putExtra(Constants.PHONE_ID, integer.getData());
                                setResult(VEHICLE_TYPE, intent);
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
                        Log.i(TAG, "onError: ");
                    }

                });
    }
}
