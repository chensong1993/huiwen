package com.shanghai.logistics.util;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shanghai.logistics.R;

public class RvLineUtil {
    //
    public static void Line(RecyclerView rv, Activity activity,int height){
        rv.addItemDecoration(new RecycleViewDivider(activity, LinearLayoutManager.HORIZONTAL, height, ContextCompat.getColor(activity, R.color.hui)));
    }
}
