package com.shanghai.logistics.ui.user_activity.home_detail;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.connectors.user.UserCarModelConnector;
import com.shanghai.logistics.models.entity.user.CarLengthEntity;
import com.shanghai.logistics.models.entity.user.CarModelEntity;
import com.shanghai.logistics.presenters.user.UserCarModelPresenter;
import com.shanghai.logistics.ui.driver_activity.AddLineActivity;
import com.shanghai.logistics.ui.logistics_activity.AddDriverListActivity;
import com.shanghai.logistics.ui.logistics_fragment.home.FindGoodsFragment;
import com.shanghai.logistics.ui.user_activity.release.ShipmentsActivity;
import com.shanghai.logistics.ui.user_adapter.UserCarLengthAdapter;
import com.shanghai.logistics.ui.user_adapter.UserCarModelAdapter;
import com.shanghai.logistics.ui.user_fragment.UserReleaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 车长
 * */
public class VehicleLengthActivity extends BaseActivity<UserCarModelPresenter> implements UserCarModelConnector.View {

    private final String TAG = "VehicleLengthActivity";
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.et_length)
    EditText mEtContent;
    @BindView(R.id.tv_length_confirm)
    TextView mTvConfirm;
    @BindView(R.id.ll_car_length)
    LinearLayout mLlCarLength;
    @BindView(R.id.rv_car_model)
    RecyclerView mRvCarModel;
    @BindView(R.id.rv_car_length)
    RecyclerView mRvCarLength;
    String mCbContent = "";
    String mCustomLength = "";
    String VehicleType;
    int ActivityType;
    Intent intent;
    List<CarModelEntity> mCarModelList;
    List<CarLengthEntity> mCarLengthList;
    List<String> mCarList;
    //车型
    UserCarModelAdapter mCarModelAdapter;
    UserCarLengthAdapter mCarLengthAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_vehicle_length;
    }

    @OnClick({R.id.img_back, R.id.tv_length_confirm})
    void onClicks(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                if (mRvCarModel.getVisibility() == View.GONE) {
                    mRvCarModel.setVisibility(View.VISIBLE);
                    mTvConfirm.setVisibility(View.GONE);
                    mLlCarLength.setVisibility(View.GONE);
                } else {
                    finish();
                }
                break;
            case R.id.tv_length_confirm:
                if (!mEtContent.getText().toString().isEmpty()) {
                    mCustomLength = "," + mEtContent.getText().toString();
                }
                if (mEtContent.getText().toString().isEmpty() && mCbContent.isEmpty()) {
                    ToastUtils.show("必须选择车长");
                    return;
                }

                switch (ActivityType) {
                    case Constants.SEND_ORDERS:
                        intent = new Intent(this, UserReleaseFragment.class);
                        intent.putExtra(Constants.VEHICLE_TYPE, VehicleType + mCbContent + mCustomLength);
                        setResult(1, intent);
                        finish();
                        break;
                    case Constants.PlaceAnOrder:
                        intent = new Intent(this, PlaceAnOrderActivity.class);
                        intent.putExtra(Constants.VEHICLE_TYPE, VehicleType + mCbContent + mCustomLength);
                        setResult(PlaceAnOrderActivity.VEHICLE_TYPE, intent);
                        finish();
                        break;
                    case Constants.SHIPMENTS:
                        intent = new Intent(this, ShipmentsActivity.class);
                        intent.putExtra(Constants.VEHICLE_TYPE, VehicleType + mCbContent + mCustomLength);
                        setResult(1, intent);
                        finish();
                    case Constants.ADD_LINE:
                        intent = new Intent(this, AddLineActivity.class);
                        intent.putExtra(Constants.VEHICLE_TYPE, VehicleType + mCbContent + mCustomLength);
                        setResult(1, intent);
                        finish();
                        break;
                    case Constants.ADD_DRIVER_LIST_ACTIVITY:
                        intent = new Intent(this, AddDriverListActivity.class);
                        intent.putExtra(Constants.VEHICLE_TYPE, VehicleType + mCbContent + mCustomLength);
                        setResult(1, intent);
                        finish();
                        break;
                    case Constants.FIND_GOODS_FRAGMENT2:
                        intent = new Intent(this, FindGoodsFragment.class);
                        intent.putExtra(Constants.VEHICLE_TYPE, VehicleType + mCbContent + mCustomLength);
                        setResult(1, intent);
                        finish();
                        break;
                }

                // Log.i("tv_length_confirm", i + "tv_length_confirm: " + mCbContent);
                break;

        }

    }

    @Override
    protected void initEventAndData() {
        ActivityType = getIntent().getIntExtra(Constants.ACTIVITY_TYPE, 0);
        mCarModelList = new ArrayList<>();
        mCarLengthList = new ArrayList<>();
        //选择的长度
        mCarList = new ArrayList<>();

        mPresenter.getUserCarModel();
        mPresenter.getUserCarLength();
        //获取汽车类型
        mCarModelAdapter = new UserCarModelAdapter(mCarModelList);
        mRvCarModel.setLayoutManager(new GridLayoutManager(this, 4));
        mRvCarModel.setAdapter(mCarModelAdapter);
        mCarModelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VehicleType = mCarModelAdapter.getItem(position).getCarModel();
                mRvCarModel.setVisibility(View.GONE);
                mLlCarLength.setVisibility(View.VISIBLE);
                mTvConfirm.setVisibility(View.VISIBLE);

            }
        });
        //获取汽车长度
        mCarLengthAdapter = new UserCarLengthAdapter(mCarLengthList);
        mRvCarLength.setLayoutManager(new GridLayoutManager(this, 4));
        mRvCarLength.setAdapter(mCarLengthAdapter);
        mRvCarLength.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CheckBox checkBox = view.findViewById(R.id.tv_length);
                if (checkBox.isChecked() == false) {
                    if (mCarList.size() > 2) {
                        ToastUtils.show("最多只能选三种哦");
                        checkBox.setBackgroundColor(ContextCompat.getColor(VehicleLengthActivity.this, R.color.black));
                    } else {
                        mCarList.add(mCarLengthAdapter.getItem(position).getCarLength());
                        checkBox.setBackgroundColor(ContextCompat.getColor(VehicleLengthActivity.this, R.color.red));
                    }

                } else {
                    mCarList.remove(mCarLengthAdapter.getItem(position).getCarLength());
                    checkBox.setBackgroundColor(ContextCompat.getColor(VehicleLengthActivity.this, R.color.black));
                }

                String mLength = mCarList.toString();

                mLength = mLength.replace("[", "");
                mCbContent = mLength.replace("]", "");
                Log.i(TAG, mLength);
            }
        });
        //  VehicleType = getIntent().getStringExtra("VehicleLengthActivity");
    }


    @Override
    public void UserCarModel(List<CarModelEntity> e) {
        mCarModelAdapter.setNewData(e);
    }

    @Override
    public void UserCarModelErr(String s) {
        ToastUtils.show("车型获取失败");
    }

    @Override
    public void UserCarLength(List<CarLengthEntity> e) {
        mCarLengthAdapter.setNewData(e);
    }

    @Override
    public void UserCarLengthErr(String err) {
        ToastUtils.show("车长获取失败");
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
