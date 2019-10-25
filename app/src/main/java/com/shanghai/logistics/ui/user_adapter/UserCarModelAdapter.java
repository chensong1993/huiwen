package com.shanghai.logistics.ui.user_adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.models.entity.user.CarModelEntity;
import com.shanghai.logistics.models.entity.user.LinePhoneEntity;

import java.util.List;

public class UserCarModelAdapter extends BaseQuickAdapter<CarModelEntity, BaseViewHolder> {

    public UserCarModelAdapter(@Nullable List<CarModelEntity> data) {
        super(R.layout.item_car_modle, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarModelEntity item) {
        Glide.with(mContext).load(Constants.MAIN_URL + item.getCarImg()).into((ImageView) helper.getView(R.id.img_car));
        helper.setText(R.id.tv_car_model, item.getCarModel());

    }
}
