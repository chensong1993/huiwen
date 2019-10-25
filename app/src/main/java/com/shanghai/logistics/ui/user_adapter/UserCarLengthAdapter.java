package com.shanghai.logistics.ui.user_adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.models.entity.user.CarLengthEntity;
import com.shanghai.logistics.models.entity.user.CarModelEntity;

import java.util.List;

public class UserCarLengthAdapter extends BaseQuickAdapter<CarLengthEntity, BaseViewHolder> {

    public UserCarLengthAdapter(@Nullable List<CarLengthEntity> data) {
        super(R.layout.item_car_length, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarLengthEntity item) {
        helper.setText(R.id.tv_length, item.getCarLength())
                .addOnClickListener(R.id.tv_length)
                .linkify(R.id.tv_length);;

    }
}
