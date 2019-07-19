package com.shanghai.logistics.ui.user_adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.models.entity.user.HomeListEntity;
import com.shanghai.logistics.models.entity.user.LinePhoneEntity;

import java.util.List;

public class UserLinePhoneAdapter extends BaseQuickAdapter<LinePhoneEntity, BaseViewHolder> {

    public UserLinePhoneAdapter(@Nullable List<LinePhoneEntity> data) {
        super(R.layout.item_line_phone, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LinePhoneEntity item) {
        helper.setText(R.id.tv_k, item.getName());
        helper.setText(R.id.tv_phone, item.getPhone());

    }
}
