package com.shanghai.logistics.ui.logistics_adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.models.entity.user.HomeListEntity;
import com.shanghai.logistics.models.entity.user.UserShopEntity;

import java.util.ArrayList;
import java.util.List;

public class LShopListAdapter extends BaseQuickAdapter<UserShopEntity.DedicatedLine, BaseViewHolder> {

    public LShopListAdapter(@Nullable List<UserShopEntity.DedicatedLine> data) {
        super(R.layout.item_user_home, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserShopEntity.DedicatedLine item) {
        helper.setText(R.id.tv_city_zhuanxian, item.getLineName());
        helper.setText(R.id.tv_city, item.getServiceAddress());
        helper.setText(R.id.tv_time, item.getAging() + "天"); //时间
        helper.setText(R.id.tv_wight, item.getWeight() + "/吨");
        helper.setText(R.id.tv_square, item.getVolume() + "/方");
       // helper.setText(R.id.tv_sales_volume, "月销" + item.getTotalSales() + "笔");
        // 加载网络图片
        Glide.with(mContext).load(Constants.MAIN_URL + item.getMainImg()).into((ImageView) helper.getView(R.id.img_title));

    }
}
