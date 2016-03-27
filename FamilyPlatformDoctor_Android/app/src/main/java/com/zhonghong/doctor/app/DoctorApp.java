package com.zhonghong.doctor.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.DisplayMetrics;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.zhonghong.doctor.R;
import com.zhonghong.doctor.net.MetaUtil;
import com.zhonghong.doctor.util.ImageCacheSavePathUtil;
import com.zhonghong.doctor.util.SpsUtil;
import com.zhonghong.doctor.util.mode.ExitAccountNotice;

import java.io.File;


public class DoctorApp extends Application {

    private static int screenWidth;

    private static int screenHeight;

    public static final String FRESCO_CACHE = "fresco";

    private static final int nPageSize = 10;

    private static Context mTotalContext;


    @Override
    public void onCreate() {
        // TODO 自动生成的方法存根
        super.onCreate();
        mTotalContext = this.getApplicationContext();

        if (getMainPackageName().equals(getCurProcessName())) {
            //androidManifest  参数工具
            MetaUtil.getInstance().initContext(this.getApplicationContext());
            //Fresco
            initFresco(this);
        }

        //初始化 AppStatusUtil
        this.registerActivityLifecycleCallbacks(AppStatusUtil.getInstance());
    }


    private static void initFresco(Context context) {
        final int MAX_DISK_CACHE_VERY_LOW_SIZE = 10 * ByteConstants.MB;//默认图极低磁盘空间缓存的最大值
        final int MAX_DISK_CACHE_LOW_SIZE = 30 * ByteConstants.MB;//默认图低磁盘空间缓存的最大值
        final int MAX_DISK_CACHE_SIZE = 100 * ByteConstants.MB;//默认图磁盘缓存的最大值


        //默认图片的磁盘配置
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder()
                .setBaseDirectoryPath(new File(ImageCacheSavePathUtil.getImageSavePath(context)))//缓存图片基路径
                .setBaseDirectoryName(FRESCO_CACHE)//文件夹名
                .setMaxCacheSize(MAX_DISK_CACHE_SIZE)//默认缓存的最大大小。
                .setMaxCacheSizeOnLowDiskSpace(MAX_DISK_CACHE_LOW_SIZE)//缓存的最大大小,使用设备时低磁盘空间。
                .setMaxCacheSizeOnVeryLowDiskSpace(MAX_DISK_CACHE_VERY_LOW_SIZE)//缓存的最大大小,当设备极低磁盘空间
                .build();

        ImagePipelineConfig.Builder builder = ImagePipelineConfig.newBuilder(context);
        builder.setMainDiskCacheConfig(diskCacheConfig);

        //FACEBOOK 图片加载工具
        Fresco.initialize(context, builder.build());
    }


    //--------------------------------------------------------------------------------------------------------

    /**
     * 设置屏幕宽度，在MainActivity,LoadingActivity调用
     */
    public static void setScreenWidth(int screenWidth) {
        DoctorApp.screenWidth = screenWidth;
        if (screenWidth != 0)
            SpsUtil.editInt(R.string.SCREEN_WIDTH, screenWidth);
    }

    public static int getScreenWidth() {
        if (screenWidth == 0)
            screenWidth = SpsUtil.getInt(R.string.SCREEN_WIDTH);
        return screenWidth;
    }

    /**
     * 设置屏幕高度，在MainActivity,LoadingActivity调用
     */
    public static void setScreenHeight(int screenHeight) {
        DoctorApp.screenHeight = screenHeight;
        if (screenHeight != 0)
            SpsUtil.editInt(R.string.SCREEN_HEIGHT, screenHeight);
    }

    public static int getScreenHeight() {
        if (screenHeight == 0)
            screenHeight = SpsUtil.getInt(R.string.SCREEN_HEIGHT);
        return screenHeight;
    }

    //--------------------------------------------------------------------------------------------------------


    /**
     * 获取用户ID
     */
    public static int getUserID() {
        return 0;
    }


    /**
     * 获取app的版本号
     *
     * @return 返回当前的版本号
     */
    public static int getVersionCode() {
        try {
            return mTotalContext.getPackageManager().getPackageInfo(mTotalContext.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取app的版本名称
     *
     * @return 返回当前的版本名称
     */
    public static String getVersionName() {
        try {
            return mTotalContext.getPackageManager().getPackageInfo(mTotalContext.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前进程名
     */
    public static String getCurProcessName() {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) mTotalContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    public static String getMainPackageName() {
        try {
            return mTotalContext.getPackageManager().getPackageInfo(mTotalContext.getPackageName(), 0).packageName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取屏幕宽高
     */
    public static void initScreen(Activity mActivity) {
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        DoctorApp.setScreenWidth(dm.widthPixels);
        DoctorApp.setScreenHeight(dm.heightPixels);
    }

    public static void logoutApp(Context mContext) {

        //清理数据缓存
        SpsUtil.cleanPreferences(mContext);
        SpsUtil.editInt(R.string.GUIDE_VERSION_CODE, DoctorApp.getVersionCode());
        SpsUtil.editBoolean(R.string.GUIDE_IS_HAD, true);
        SpsUtil.editBoolean(R.string.IS_NO_FIRST_INSTALL, true);
        ExitAccountNotice.getInstance().notifyObserver(0);
    }


    public static Context getTotalContext() {
        return mTotalContext;
    }

    public static int getnPageSize() {
        return nPageSize;
    }
}
