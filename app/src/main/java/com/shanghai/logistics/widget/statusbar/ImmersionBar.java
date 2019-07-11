package com.shanghai.logistics.widget.statusbar;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author chensong
 * @date 2019/4/24 14:31
 */
public class ImmersionBar {
    public static void Util(Activity activity, TextView textView, int layoutType) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            textView.setVisibility(View.GONE);
        }else {
            //获取状态栏高度
            int statusBarHeight;
            int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
                switch (layoutType) {
                    case 0:
                        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) textView.getLayoutParams();
                        lp.height = statusBarHeight;
                        textView.setLayoutParams(lp);
                        break;
                    case 1:
                        RelativeLayout.LayoutParams lp1 = (RelativeLayout.LayoutParams) textView.getLayoutParams();
                        lp1.height = statusBarHeight;
                        textView.setLayoutParams(lp1);
                        break;
                    default:

                        break;

                }
            }
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            textView.setVisibility(View.VISIBLE);
        }

}
}
