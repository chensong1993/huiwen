package com.shanghai.logistics.ui.logistics_adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.models.entity.logistics.LOrderEntity;
import com.shanghai.logistics.models.entity.user.UserShopEntity;

import java.util.List;

public class LOrderListAdapter extends BaseQuickAdapter<LOrderEntity, BaseViewHolder> {

    public LOrderListAdapter(@Nullable List<LOrderEntity> data) {
        super(R.layout.item_billing, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LOrderEntity item) {
        helper.setText(R.id.tv_realName, item.getRealName());
        helper.setText(R.id.tv_startAddress, item.getStartAddress());
        helper.setText(R.id.tv_endAddress, item.getEndAddress());
        helper.setText(R.id.tv_goods, item.getGoods());
        helper.setText(R.id.tv_pieces_weight_volume, ":" + item.getPieces() + "件," + item.getWeight() + "公斤," + item.getVolume() + "方");
        helper.setText(R.id.tv_estimatedCost, "费用：￥" + item.getEstimatedCost());
        helper.setText(R.id.tv_loadingTime, item.getLoadingTime());
        // 加载网络图片
        Glide.with(mContext).load(Constants.MAIN_URL + item.getHeadImgUrl()).into((ImageView) helper.getView(R.id.img_headImgUrl));

    }
}
