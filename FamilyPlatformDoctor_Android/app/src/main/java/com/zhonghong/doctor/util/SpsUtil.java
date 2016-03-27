package com.zhonghong.doctor.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.zhonghong.doctor.R;
import com.zhonghong.doctor.app.DoctorApp;

import java.util.ArrayList;
import java.util.List;

public class SpsUtil {
    private static SharedPreferences preferences;
    private static Context mContext;

    /**
     * 初始化preferences
     */
    private static void initPreferences() {
        if (mContext == null)
            mContext = DoctorApp.getTotalContext();
        if (preferences == null)
            preferences = mContext.getSharedPreferences(mContext.getString(R.string.Doctor), Context.MODE_PRIVATE);
    }

    public static int getInt(int nResID) {
        initPreferences();
        return preferences.getInt(mContext.getString(nResID), 0);

    }

    public static int getInt(String strKey) {
        initPreferences();
        return preferences.getInt(strKey, 0);
    }

    public static boolean getBoolean(int nResID) {
        initPreferences();
        return preferences.getBoolean(mContext.getString(nResID), false);
    }

    public static String getString(int nResID) {
        initPreferences();
        return preferences.getString(mContext.getString(nResID), "");
    }

    public static String getString(String strKey) {
        initPreferences();
        return preferences.getString(strKey, "");
    }

    public static void editString(int nResID, String strContent) {
        initPreferences();
        if (TextUtils.isEmpty(strContent))
            strContent = "";
        preferences.edit().putString(mContext.getString(nResID), strContent).commit();
    }

    public static void editString(String strKey, String strContent) {
        initPreferences();
        preferences.edit().putString(strKey, strContent).commit();
    }

    public static void editLong(int nResID, long content) {
        initPreferences();
        preferences.edit().putLong(mContext.getString(nResID), content).commit();
    }

    public static void editLong(String strKey, long content) {
        initPreferences();
        preferences.edit().putLong(strKey, content).commit();
    }


    public static void editInt(String strKey, int nContent) {
        initPreferences();
        preferences.edit().putInt(strKey, nContent).commit();
    }

    public static void editInt(int nResID, int content) {
        initPreferences();
        preferences.edit().putInt(mContext.getString(nResID), content).commit();
    }

    public static void editBoolean(int nResID, boolean content) {
        initPreferences();
        preferences.edit().putBoolean(mContext.getString(nResID), content).commit();
    }


//    public static void editStringList(int nResID, List<String> list) {
//        if (list == null || list.size() == 0) {
//            editString(nResID, "");
//            return;
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < list.size(); i++) {
//            sb.append(list.get(i));
//            if (i != list.size() - 1)
//                sb.append(",");
//        }
//        String save = sb.toString();
//        editString(nResID, save);
//    }
//
//    public static List<String> getStringList(int nResID) {
//        String textAll = getString(nResID);
//        String[] values = null;
//        try {
//            values = textAll.split(",");
//        } catch (Exception e) {
//        }
//
//        List<String> list = new ArrayList<String>();
//        if (values != null && values.length > 0)
//            for (int j = 0; j < values.length; j++)
//                if (!TextUtils.isEmpty(values[j]))
//                    list.add(values[j]);
//        return list;
//    }

    public static List<String> getArrayList(int nResID) {
        initPreferences();
        String[] arrays = mContext.getResources().getStringArray(nResID);
        if (arrays == null || arrays.length <= 0)
            return new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++)
            list.add(arrays[i]);
        return list;
    }

    /**
     * 清除资料
     *
     * @param mContext
     */
    public static void cleanPreferences(Context mContext) {
        initPreferences();
        preferences.edit().clear().commit();
    }


}
