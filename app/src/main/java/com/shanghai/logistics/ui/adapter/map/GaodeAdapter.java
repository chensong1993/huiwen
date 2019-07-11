package com.shanghai.logistics.ui.adapter.map;

import android.support.annotation.Nullable;

import com.amap.api.services.help.Tip;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.logistics.R;

import java.util.List;

public class GaodeAdapter extends BaseQuickAdapter<Tip, BaseViewHolder> {

    public GaodeAdapter(@Nullable List<Tip> data) {
        super(R.layout.item_gaode_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Tip item) {
        helper.setText(R.id.tv_title, item.getName());
        helper.setText(R.id.tv_km, item.getAddress());
    }
}
