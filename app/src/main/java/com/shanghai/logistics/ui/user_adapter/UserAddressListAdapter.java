package com.shanghai.logistics.ui.user_adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.logistics.R;
import com.shanghai.logistics.models.entity.user.AddressListEntity;
import com.shanghai.logistics.models.entity.user.LinePhoneEntity;

import java.util.List;

public class UserAddressListAdapter extends BaseQuickAdapter<AddressListEntity, BaseViewHolder> {

    public UserAddressListAdapter(@Nullable List<AddressListEntity> data) {
        super(R.layout.item_address_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressListEntity item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_phone, item.getPhone());
        helper.setText(R.id.tv_address, item.getAddress());

    }
}
