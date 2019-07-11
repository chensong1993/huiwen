package com.shanghai.logistics.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.hjq.toast.ToastUtils;
import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.shanghai.logistics.injections.components.AppComponent;
import com.shanghai.logistics.injections.components.DaggerAppComponent;
import com.shanghai.logistics.injections.modules.AppModule;
import com.shanghai.logistics.injections.modules.HttpModule;
import com.tencent.mmkv.MMKV;

import java.util.HashSet;
import java.util.Set;



/**
 * @author chensong
 * @date 2019/2/18 10:41
 */
public class App extends Application {
    private static final String TAG = App.class.getName();
    private static App instance = null;
    public static AppComponent appComponent;
    private Set<Activity> allActivities;
    public static MMKV kv;

    public static App getInstance() {
        if (instance == null) {
            synchronized (App.class) {
                if (instance == null) {
                    instance = new App();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        /**
         * 预先加载一级列表显示 全国所有城市市的数据
         */
        CityListLoader.getInstance().loadCityData(this);
        //  RongIM.init(this);
      //  refWatcher = LeakCanary.install(this);
        MMKV.initialize(this);
        kv = MMKV.defaultMMKV();
        ToastUtils.init(this);
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    /**
     *  * 分割 Dex 支持
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
      //  MultiDex.install(this);
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


}
