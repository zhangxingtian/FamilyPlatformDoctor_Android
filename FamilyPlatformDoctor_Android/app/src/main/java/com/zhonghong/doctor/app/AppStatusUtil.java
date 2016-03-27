package com.zhonghong.doctor.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by guobin.zheng on 2015/7/20.
 * 判断程序是否在运行
 */
public class AppStatusUtil implements Application.ActivityLifecycleCallbacks {

    private static boolean paused;
    private static boolean foreground;

    private Runnable check;
    private android.os.Handler handler = new android.os.Handler();

    private static AppStatusUtil INSTANCE;

    private AppStatusUtil() {
    }

    public static AppStatusUtil getInstance() {
        if (null==INSTANCE)
            INSTANCE = new AppStatusUtil();
        return INSTANCE;
    }

    public static boolean isForeground() {
        return foreground;
    }

    public boolean isBackground() {
        return !foreground;
    }


    @Override
    public void onActivityResumed(Activity activity) {
        paused = false;
        foreground = true;
        if (check != null) {
            handler.removeCallbacks(check);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        paused = true;
        if (check != null) {
            handler.removeCallbacks(check);
        }
        handler.postDelayed(check = () -> {
            if (foreground && paused) {
                foreground = false;
            }
        }, 1000);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


}
