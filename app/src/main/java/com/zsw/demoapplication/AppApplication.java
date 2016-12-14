package com.zsw.demoapplication;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.baidu.mobads.AdSettings;
import com.baidu.mobads.AdView;
import com.baidu.mobads.AppActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.http.session.HttpSession;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.zsw.demoapplication.constant.Constant;

import net.youmi.android.AdManager;

import java.io.File;
import java.util.List;

import cn.trinea.android.common.configure.Config;
import cn.trinea.android.common.util.PreferenceHelper;

/**
 * Created by sunny on 15/10/15.
 */
public class AppApplication extends Application {

    private static Context context;
    private static PreferenceHelper preferenceHelper;

    // user your appid the key.
    private static final String APP_ID = "2882303761517479052";
    // user your appid the key.
    private static final String APP_KEY = "5741747965052";



    // 此TAG在adb logcat中检索自己所需要的信息， 只需在命令行终端输入 adb logcat | grep
    // com.xiaomi.mipushdemo
    public static final String TAG = "com.zhixiao";


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        Fresco.initialize(context);
        initImageLoader(context);
        initBaiduAds();
        Config.PREFERENCE_NAME = Constant.WAIBAO_SHOW;
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 获取数据存储
     *
     * @return
     */
    public static PreferenceHelper getPreferenceHelper() {
        synchronized (AppApplication.class) {
            if (preferenceHelper == null) {
                preferenceHelper = new PreferenceHelper(context);
            }
        }
        return preferenceHelper;
    }


    /**
     * imageLoader 加载
     *
     * @param context
     */
    private static void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getCacheDirectory(context);
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        //ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator()).discCache(new UnlimitedDiscCache(cacheDir)).tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs()
        //.build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(5)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(10 * 1024 * 1024))
                .memoryCacheSize(10 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    /**
     * 初始化百度广告
     */
    public void initBaiduAds(){
        //人群属性
        AdSettings.setKey(new String[]{"baidu", "中国"});
        // 设置'广告着陆页'颜色主题
        AppActivity.setActionBarColorTheme(AppActivity.ActionBarColorTheme.ACTION_BAR_WHITE_THEME);
        // 另外，也可设置劢作栏中单个元素的颜色, 颜色参数为四段制，0xFF(透明度, 一般填FF)DE(红) DA(绿) DB(蓝)
        AppActivity.getActionBarColorTheme().setBackgroundColor(
                0xFFDEDADB);
    }

}
