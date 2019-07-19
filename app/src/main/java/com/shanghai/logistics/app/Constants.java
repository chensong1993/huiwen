package com.shanghai.logistics.app;

import android.os.Environment;

import java.io.File;

/**
 * @author chensong
 * @date 2019/4/15 10:45
 */
public class Constants {
    //================= PATH ====================
    //okhttp缓存路径
    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=360";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "chinaipo" + File.separator + "StockNews";

    //================= URL====================
    //服务器正式地址：http://xyzaa.com/xinya/
    // 服务器测试地址：http://xyzaa.com:8080/xinya/

    public static final String MAIN_URL = "http://xyzaa.com:8080/xinya/";

    public static final int ACCESS_FINE_LOCATION = 1001;//位置权限请求

    public static final String USER_PHONE = "username";

    public static final String USER_PASSWORD = "user_password";

    public static final String ACTIVITY_TYPE = "activityType";

    /*
     * 各种传值
     * */
    public static final String FRAGMENT_INDEX = "fragment_index";
    public static final String All_VALUE = "all_value";

    public static final String STORE_ID = "store_id";

    public static final String DEDICATED_LINE_PHONE_ID = "dedicatedLinePhoneId";

    public static final String VEHICLE_TYPE = "vehicle_type";

    public static final String STORE_ACCOUNT ="store_account";

    public static final String SELECTED_USER_TYPE = "selected_user_type";

    public static final String PHONE = "PHONE";

    public static final String NICK_NAME = "nick_name";

    public static final String HEAD_URL = "head_url";

    public static final String STORE_LIST = "store_list";

    public static final String PHONE_ID = "phone_id";

    public static final String BILLING_VALUE ="billing_value";

    public static final String START_TYPE="start_type";
    //扫一扫返回结果
    public static final int REQUEST_CODE_SCAN = 0x001;

    public static final int USER_CLIENT = 1;

    public static final int DRIVER_CLIENT = 2;

    public static final int LOGISTICS_CLIENT = 3;

    public static final String ADDRESS_NAME = "address_name";

    public final static String FILE_PROVIDER = "com.shanghai.logistics.FileProvider";//7.0共享相机共享路径

    public static final int CODE_GALLERY_REQUEST = 0xa0;

    public static final int CODE_CAMERA_REQUEST = 0xa1;

    public static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;

    public static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    /*
     * 判断从不同页面跳转相同activity或者fragment
     * */
    public static final int SEND_ORDERS = 101;

    public static final int PlaceAnOrder = 102;

    public static final int SHIPMENTS = 103;

    public static final int ADD_LINE = 104;

    public static final int ADD_DRIVER_LIST_ACTIVITY = 105;

    public static final int FIND_GOODS_FRAGMENT2 = 106;

    /*
     *  fragment
     * */
    public static final int DRIVER_ME_FRAGMENT = 201;

    public static final int USER_HOME_FRAGMENT = 202;

    public static final int LOGISTICS_ME_FRAGMENT = 203;

    public static final int OPERATING_CENTER_FRAGMENT = 204;

    /*
     * activity
     * */
    public static final int BILLING_ACTIVITY = 301;

    public static final int USER_BACK_ACTIVITY = 302;

    public static final int ADD_STORES_ACTIVITY = 303;

    public static final int HOME_DETAIL_ACTIVITY = 304;
}
