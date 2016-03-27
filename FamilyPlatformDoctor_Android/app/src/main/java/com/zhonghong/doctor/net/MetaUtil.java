package com.zhonghong.doctor.net;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * Created by guobin.zheng on 2014/11/4.
 *
 * 要在application里面初始化该类
 */
public class MetaUtil {

    private Context context;

    private static MetaUtil metaUtil;


    private MetaUtil() {

    }

    public static MetaUtil getInstance() {
        if (null == metaUtil) {
            synchronized (MetaUtil.class) {
                if (null == metaUtil)
                    metaUtil = new MetaUtil();
            }
        }
        return metaUtil;
    }


    public void initContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    /**
     * 获取iMall AndroidManifest的meta属性
     * @param name meta的key值
     * @return 返回meta的value值
     */
    public String getMetaString(String name) {
        if (context == null)
            throw new NullPointerException("Please init the context");
        if (name == null)
            return null;

        Bundle metaData = null;
        String value = null;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                value = metaData.getString(name);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    public boolean getMetaBoolean(String name) {
        if (context == null)
            throw new NullPointerException("Please init the context");
        if (name == null)
            return false;
        Bundle metaData = null;
        boolean value = false;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                value = metaData.getBoolean(name);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

}
