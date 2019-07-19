package com.shanghai.logistics.ui.logistics_adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.models.entity.logistics.LUserInfoEntity;
import com.shanghai.logistics.models.entity.user.AddressListEntity;

import java.util.List;

public class LogisticsMyShopAdapter extends BaseQuickAdapter<LUserInfoEntity.Store, BaseViewHolder> {
    String imgUrl;

    public LogisticsMyShopAdapter(@Nullable List<LUserInfoEntity.Store> data) {
        super(R.layout.item_my_shop, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, LUserInfoEntity.Store item) {
        String[] strings = item.getStoreImg().split(",");

        for (int i = 0; i < strings.length; i++) {
            imgUrl = strings[0];
        }
        Glide.with(mContext).load(Constants.MAIN_URL+imgUrl).into((ImageView) helper.getView(R.id.img_store));
        helper.setText(R.id.tv_storeName, item.getStoreName());
        helper.setText(R.id.tv_storeAddress, item.getStoreAddress());
    }
}
