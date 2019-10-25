package com.shanghai.logistics.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionOwner;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.util.ActivityCollector;

import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

public abstract class SimpleActivity extends SwipeBackActivity {

    public static final int REQUEST_READ_PHONE_STATE = 1;
    protected Activity mContext;
    private Unbinder mUnBinder;
    public static final int PRC_PHOTO_PICKER = 1;
    public static final int RC_CHOOSE_PHOTO = 1;
    public static final int RC_PHOTO_PREVIEW = 2;
    //  public static final String TAG="SimpleActivity";
    public String mLoginPhone;
    public final String TAG = "SimpleActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        if (App.kv.decodeString(Constants.USER_PHONE) != null) {
            mLoginPhone = App.kv.decodeString(Constants.USER_PHONE);
        }
        onViewCreated();
        initEventAndData();
        Log.i(TAG, "onCreate: " + mLoginPhone);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            Log.i(TAG, "onCreate: " + getAllBytesWifi());
//        }else {
//            ToastUtils.show("版本太低");
//        }
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

    private boolean hasPermissionToReadNetworkStats() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        final AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), getPackageName());
        if (mode == AppOpsManager.MODE_ALLOWED) {
            return true;
        }

        requestReadNetworkStats();
        return false;
    }

    // 打开“有权查看使用情况的应用”页面
    private void requestReadNetworkStats() {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);
    }


//    /**
//     * 本机使用的 wifi 总流量
//     */
//    @SuppressLint("NewApi")
//NetworkStatsManager networkStatsManager;
//    NetworkStats.Bucket bucket;
//    public long getAllBytesWifi() {
//        try {
//                bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI,
//                        "",
//                        0,
//                        System.currentTimeMillis());
//
//        } catch (RemoteException e) {
//            return -1;
//        }
//        //这里可以区分发送和接收
//        return bucket.getTxBytes() + bucket.getRxBytes();
//    }
//
//    /**
//     * 本机使用的 mobile 总流量
//     */
//    @SuppressLint("NewApi")
//    public long getAllBytesMobile() {
//        NetworkStats.Bucket bucket;
//        try {
//            bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE,
//                    getSubscriberId(this, ConnectivityManager.TYPE_MOBILE),
//                    0,
//                    System.currentTimeMillis());
//        } catch (RemoteException e) {
//            return -1;
//        }
//        //这里可以区分发送和接收
//        return bucket.getTxBytes() + bucket.getRxBytes();
//    }
//
//    @SuppressLint({"MissingPermission"})
//    private String getSubscriberId(Context context, int networkType) {
//        if (ConnectivityManager.TYPE_MOBILE == networkType) {
//            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            return tm.getSubscriberId();
//        }
//        return "";
//    }
//
//    /**
//     * 获取指定应用 wifi 发送的当天总流量
//     *
//     * @param packageUid 应用的uid
//     * @return
//     */
//    @SuppressLint("NewApi")
//    public long getPackageTxDayBytesWifi(int packageUid) {
//        NetworkStats networkStats = null;
//        networkStats = networkStatsManager.queryDetailsForUid(
//                ConnectivityManager.TYPE_WIFI,
//                "",
//                getTimesmorning(),
//                System.currentTimeMillis(),
//                packageUid);
//        NetworkStats.Bucket bucket = new NetworkStats.Bucket();
//        networkStats.getNextBucket(bucket);
//        return bucket.getTxBytes();
//    }
//
//    /**
//     * 获取当天的零点时间
//     *
//     * @return
//     */
//    public static long getTimesmorning() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        return (cal.getTimeInMillis());
//    }
//
//    //获得本月第一天0点时间
//    @SuppressLint("WrongConstant")
//    public static int getTimesMonthmorning() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
//        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
//        return (int) (cal.getTimeInMillis());
//    }
//
//    /**
//     * 根据包名获取uid
//     *
//     * @param context     上下文
//     * @param packageName 包名
//     */
//    public static int getUidByPackageName(Context context, String packageName) {
//        int uid = -1;
//        PackageManager packageManager = context.getPackageManager();
//        try {
//            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
//            uid = packageInfo.applicationInfo.uid;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return uid;
//    }
}
