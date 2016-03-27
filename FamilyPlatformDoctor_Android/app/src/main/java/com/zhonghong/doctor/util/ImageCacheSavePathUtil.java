package com.zhonghong.doctor.util;

import android.content.Context;
import android.os.Environment;

import com.zhonghong.doctor.R;

import java.io.File;

public class ImageCacheSavePathUtil {

    public static String getImageSavePath(Context context) {
        File dataDir;
        String path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dataDir = Environment.getExternalStorageDirectory();
            path = context.getString(R.string.SDcardPath);
        } else {
            dataDir = Environment.getDataDirectory();
            path = context.getString(R.string.DataPath);
        }
        StringBuffer DataPath = new StringBuffer();
        StringBuffer cachePath = new StringBuffer();
        DataPath.append(dataDir.getPath()).append(path);
        cachePath.append(DataPath.toString()).append(".cache");
        File cache = new File(cachePath.toString());
        if (!cache.exists())
           cache.mkdirs();
        return DataPath.toString();
    }

    public static String getImageSaveToPic(Context context) {
        File dataDir;
        String path;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            dataDir = Environment.getExternalStorageDirectory();
            path = context.getString(R.string.PicPath);
        } else {
            dataDir = Environment.getDataDirectory();
            path = context.getString(R.string.PicPath);
        }
        StringBuffer DataPath = new StringBuffer();
        StringBuffer cachePath = new StringBuffer();
        DataPath.append(dataDir.getPath()).append(path);
        cachePath.append(DataPath.toString());
        File cache = new File(cachePath.toString());
        if (!cache.exists()) {
            cache.mkdirs();
        }
        return DataPath.toString();
    }

}
