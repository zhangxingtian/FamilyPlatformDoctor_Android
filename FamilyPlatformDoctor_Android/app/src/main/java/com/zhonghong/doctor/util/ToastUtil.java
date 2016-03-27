package com.zhonghong.doctor.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;
import com.zhonghong.doctor.app.DoctorApp;

public class ToastUtil {

    private static Toast longToast;
    private static Toast shortToast;
    private static Context mContext;

    private static void initContext() {
        if (mContext == null)
            mContext = DoctorApp.getTotalContext();
    }


    public static void showToastShort(int strId) {
        initContext();
        if (shortToast != null)
            shortToast.cancel();
        if (strId != 0) {
            shortToast = Toast.makeText(mContext, strId, Toast.LENGTH_SHORT);
            shortToast.setGravity(Gravity.TOP, 0, 100);
            shortToast.show();
        }
    }

    public static void showToastShort(String str) {
        initContext();
        if (shortToast != null)
            shortToast.cancel();
        if (!TextUtils.isEmpty(str)) {
            shortToast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
            shortToast.setGravity(Gravity.TOP, 0, 100);
            shortToast.show();
        }
    }

    public static void showToastLong(int strId) {
        initContext();
        if (longToast != null)
            longToast.cancel();
        if (strId != 0) {
            longToast = Toast.makeText(mContext, strId, Toast.LENGTH_LONG);
            longToast.setGravity(Gravity.TOP, 0, 100);
            longToast.show();
        }
    }

    public static void showToastLong(String str) {
        initContext();
        if (longToast != null)
            longToast.cancel();
        if (!TextUtils.isEmpty(str)) {
            longToast = Toast.makeText(mContext, str, Toast.LENGTH_LONG);
            longToast.setGravity(Gravity.TOP, 0, 100);
            longToast.show();
        }
    }

}
