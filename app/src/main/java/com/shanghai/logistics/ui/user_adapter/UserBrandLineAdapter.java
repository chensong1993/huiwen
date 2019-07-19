package com.shanghai.logistics.ui.user_adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.models.entity.user.BrandLineEntity;
import com.shanghai.logistics.models.entity.user.LinePhoneEntity;

import java.util.List;

public class UserBrandLineAdapter extends BaseQuickAdapter<BrandLineEntity, BaseViewHolder> {
    String imgUrl;

    public UserBrandLineAdapter(@Nullable List<BrandLineEntity> data) {
        super(R.layout.item_brand_line, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BrandLineEntity item) {
        String[] strings = item.getStoreImg().split(",");

        for (int i = 0; i < strings.length; i++) {
            imgUrl = strings[0];
        }
        Glide.with(mContext).load(Constants.MAIN_URL + imgUrl).into((ImageView) helper.getView(R.id.img_storeImg));
        helper.setText(R.id.tv_storeName, item.getStoreName());
        helper.setText(R.id.tv_store_address, item.getStoreAddress());
    }
}
