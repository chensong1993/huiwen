package com.shanghai.logistics.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionOwner;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.util.ActivityCollector;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

public abstract class SimpleActivity extends SwipeBackActivity   {

    protected Activity mContext;
    private Unbinder mUnBinder;
    public static final int PRC_PHOTO_PICKER = 1;
    public static final int RC_CHOOSE_PHOTO = 1;
    public static final int RC_PHOTO_PREVIEW = 2;
    public static final String TAG="SimpleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        initEventAndData();
       // getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.);
        ActivityCollector.addActivity(this, getClass());
//        ImmersionBar.with(this)
//                .fitsSystemWindows(true)
//                .statusBarColor(R.color.red)
//                .init();
    }



    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    protected void onViewCreated() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        //  App.refWatcher.watch(this);
        //  unregisterReceiver(receiver);
    }


    protected abstract int getLayout();

    protected abstract void initEventAndData();
}
