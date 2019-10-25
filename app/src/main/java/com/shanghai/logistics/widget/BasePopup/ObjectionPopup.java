package com.shanghai.logistics.widget.BasePopup;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;

import com.shanghai.logistics.R;

import razerdp.basepopup.BasePopupWindow;

public class ObjectionPopup extends BasePopupWindow implements View.OnClickListener {

    Activity activity;
    private ItemClickListener ItemClickListener = null;

    public interface ItemClickListener {
        void onItemClick(View view);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        ItemClickListener = itemClickListener;
    }


    public ObjectionPopup(Activity activity) {
        super(activity);
        this.activity = activity;
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        findViewById(R.id.rb_1).setOnClickListener(this);
        findViewById(R.id.rb_2).setOnClickListener(this);
        findViewById(R.id.rb_3).setOnClickListener(this);
        setAlignBackground(false);
        setPopupGravity(Gravity.CENTER);
    }




    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.item_objection);
    }


    @Override
    public void onClick(View v) {
        ItemClickListener.onItemClick(v);
    }


}
