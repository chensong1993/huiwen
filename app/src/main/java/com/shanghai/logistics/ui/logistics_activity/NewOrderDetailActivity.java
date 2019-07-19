package com.shanghai.logistics.ui.logistics_activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.connectors.logistics.LogisticsOrderDetailConnector;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.logistics.LOrderEntity;
import com.shanghai.logistics.models.services.ApiService;
import com.shanghai.logistics.models.services.LogisticsService;
import com.shanghai.logistics.models.services.UserService;
import com.shanghai.logistics.presenters.logistics.LogisticsOrderDetailPresenter;
import com.shanghai.logistics.ui.user_activity.home_detail.AddressActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.VehicleLengthActivity;
import com.shanghai.logistics.util.DataUtil;
import com.shanghai.logistics.util.FileUploadUtil;
import com.shanghai.logistics.widget.BasePopup.TipPopup;

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
import okhttp3.RequestBody;

/*
 * 新订单详情
 *
 * */
public class NewOrderDetailActivity extends BaseActivity<LogisticsOrderDetailPresenter> implements LogisticsOrderDetailConnector.View {

    private static final String TAG = "PlaceAnOrderActivity";
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_end_name)
    TextView mEtEndName;
    @BindView(R.id.et_end_phone)
    TextView mEtEndPhone;
    @BindView(R.id.et_end_address)
    TextView mEtEndAddress;
    @BindView(R.id.et_goods)
    TextView mEtGoods;
    @BindView(R.id.et_volume)
    TextView mEtVolume;
    @BindView(R.id.et_weight)
    TextView mEtWeight;
    @BindView(R.id.et_pieces)
    TextView mEtPieces;
    @BindView(R.id.et_receipt)
    TextView mEtReceipt;
    @BindView(R.id.et_remarks)
    TextView mEtRemarks;
    @BindView(R.id.et_estimatedCost)
    TextView mEtEstimatedCost;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.tv_che_type)
    TextView mTvCheType;
    @BindView(R.id.tv_pay_type)
    TextView mTvPayType;
    @BindView(R.id.ed_name)
    TextView mEtName;
    @BindView(R.id.et_phone)
    TextView mEtPhone;
    @BindView(R.id.tv_time)
    TextView mTvCreatorTime;
    @BindView(R.id.et_address)
    TextView mEtAddress;
    @BindView(R.id.tv_order_num)
    TextView mTvOrderNum;
    @BindView(R.id.tv_start_ti)
    TextView mTvStartTi;
    @BindView(R.id.tv_end_ti)
    TextView mTvEndTi;
    @BindView(R.id.tv_refuse)
    TextView mTvRefuse;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    @BindView(R.id.img_start)
    ImageView mImgStart;
    @BindView(R.id.img_end)
    ImageView mImgEnd;
    Bundle bundle;
    Intent intent;
    String orderNo;
    TipPopup tipPopup;

    @Override
    protected int getLayout() {
        return R.layout.activity_orderdetail;
    }

    @OnClick({R.id.tv_refuse, R.id.img_back, R.id.tv_confirm})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_refuse: //拒绝接单
                uploadInfo(2);
                break;
            case R.id.tv_confirm: //接单
                tipPopup = new TipPopup(this);
                tipPopup.setItemClickListener(new TipPopup.ItemClickListener() {
                    @Override
                    public void onItemClick(View view) {
                        switch (view.getId()) {
                            case R.id.tv_cancel:
                                finish();
                                break;
                            case R.id.tv_confirm:
                                uploadInfo(1);
                                break;
                        }
                    }
                });
                break;
            case R.id.img_back:
                finish();
                break;

        }
    }

    @Override
    protected void initEventAndData() {

        Bundle bundle = getIntent().getBundleExtra(Constants.All_VALUE);
        if (bundle != null) {
            orderNo = bundle.getString(Constants.All_VALUE);
            mPresenter.getLogisticsOrderDetail(orderNo);
            mTvTitle.setText("订单详情");
        }


    }

    @Override
    public void LogisticsOrderDetail(LOrderEntity lOrderEntity) {
        if (lOrderEntity.getStartDeliveryType() == 1) {
            mTvStartTi.setText("上门自提");
        } else {
            mTvStartTi.setText("送货上门");
        }
        if (lOrderEntity.getEndDeliveryType() == 1) {
            mTvEndTi.setText("上门自提");
        } else {
            mTvEndTi.setText("送货上门");
        }
        mTvOrderNum.setText("订单号：" + lOrderEntity.getOrderNo());
        mEtGoods.setText(lOrderEntity.getGoods());
        mEtVolume.setText(lOrderEntity.getVolume() + "");
        mEtWeight.setText(lOrderEntity.getWeight() + "");
        mEtPieces.setText(lOrderEntity.getPieces() + "");
        mTvDate.setText(lOrderEntity.getLoadingTime());
        mTvCreatorTime.setText("创建时间：" + lOrderEntity.getCreateTime());
        mEtName.setText(lOrderEntity.getStartUserName());
        mEtPhone.setText(lOrderEntity.getStartPhone());
        mEtAddress.setText(lOrderEntity.getStartAddress());
        mEtEndName.setText(lOrderEntity.getEndUserName());
        mEtEndPhone.setText(lOrderEntity.getEndPhone());
        mEtEndAddress.setText(lOrderEntity.getEndAddress());
        mTvCheType.setText(lOrderEntity.getCarModel());
        mTvPayType.setText(lOrderEntity.getPayType());
        mEtReceipt.setText(lOrderEntity.getReceipt());
        mEtEstimatedCost.setText(lOrderEntity.getEstimatedCost() + "");
        mEtRemarks.setText(lOrderEntity.getRemark());
        Glide.with(this).load(Constants.MAIN_URL + lOrderEntity.getHeadImgUrl()).into(mImgStart);
        Glide.with(this).load(Constants.MAIN_URL + lOrderEntity.getHeadImgUrl()).into(mImgEnd);
    }

    @Override
    public void LogisticsOrderDetailErr(String s) {
        Log.i(TAG, "LogisticsOrderDetailErr: " + s);
    }

    @SuppressLint("CheckResult")
    private void uploadInfo(int type) {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("orderNo", FileUploadUtil.requestBody(mTvOrderNum.getText().toString()));
        map.put("type", FileUploadUtil.requestBody(type + ""));
        map.put("refuseRemark", FileUploadUtil.requestBody(mEtName.getText().toString()));


        ApiService.getInstance()
                .create(LogisticsService.class, Constants.MAIN_URL)
                .storeDealWithOrder(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<ApiResponse<Integer>>() {
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
                        Log.i(TAG, "onError: ");
                    }

                });
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


}
