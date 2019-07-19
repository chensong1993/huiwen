package com.shanghai.logistics.ui.user_adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.amap.api.services.help.Tip;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.models.entity.user.HomeListEntity;

import java.util.List;

public class UserHomeListAdapter extends BaseQuickAdapter<HomeListEntity, BaseViewHolder> {

    public UserHomeListAdapter(@Nullable List<HomeListEntity> data) {
        super(R.layout.item_user_home, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeListEntity item) {
        helper.setText(R.id.tv_city_zhuanxian, item.getLineName());
        helper.setText(R.id.tv_city, item.getServiceAddress());
        helper.setText(R.id.tv_time, item.getAging() + "小时"); //时间
        helper.setText(R.id.tv_wight, item.getWeight() + "/吨");
        helper.setText(R.id.tv_square, item.getVolume() + "/方");
        helper.setText(R.id.tv_sales_volume, "月销" + item.getTotalSales() + "笔");
        // 加载网络图片
        Glide.with(mContext).load(Constants.MAIN_URL+item.getMainImg()).into((ImageView) helper.getView(R.id.img_title));

    }
}
