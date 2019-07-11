# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepclassmembers class com.zhiyi.chinaipo.util.webview_photo.JavascriptInterface{
  public *;
}
-ignorewarnings

-keep class * {
    public private *;
}
#-dontwarn icepick.**
#-keep class icepick.** { *; }
#-keep class **$$Icepick { *; }
#-keepclasseswithmembernames class * {
#    @icepick.* <fields>;
#}
#-keepnames class * { @icepick.State *;}

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#js
-keepattributes *Annotation*
-keepattributes *JavascriptInterface*

-keep class com.hjq.toast.** {*;}

-keepattributes *Annotation*

#agentwebview
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**

-keepclassmembers class com.just.agentweb.sample.common.AndroidInterface{ *; }

#沉浸式
 -keep class com.gyf.immersionbar.* {*;}
 -dontwarn com.gyf.immersionbar.**

 #baseRecyclevire
 -keep class com.chad.library.adapter.** {
 *;
 }
 -keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
 -keep public class * extends com.chad.library.adapter.base.BaseViewHolder
 -keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
      <init>(...);
 }

 #地区3级联动选择器

 -keep class com.lljjcoder.**{
 	*;
 }

 -dontwarn demo.**
 -keep class demo.**{*;}
 -dontwarn net.sourceforge.pinyin4j.**
 -keep class net.sourceforge.pinyin4j.**{*;}
 -keep class net.sourceforge.pinyin4j.format.**{*;}
 -keep class net.sourceforge.pinyin4j.format.exception.**{*;}


# 3D 地图 V5.0.0之后：
 -keep   class com.amap.api.maps.**{*;}
 -keep   class com.autonavi.**{*;}
 -keep   class com.amap.api.trace.**{*;}

# 定位
 -keep class com.amap.api.location.**{*;}
 -keep class com.amap.api.fence.**{*;}
 -keep class com.autonavi.aps.amapapi.model.**{*;}

# 搜索
 -keep   class com.amap.api.services.**{*;}

 #2D地图
 -keep class com.amap.api.maps2d.**{*;}
 -keep class com.amap.api.mapcore2d.**{*;}

 #导航
 -keep class com.amap.api.navi.**{*;}
 -keep class com.autonavi.**{*;}