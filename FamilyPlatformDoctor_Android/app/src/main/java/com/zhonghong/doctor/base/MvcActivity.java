package com.zhonghong.doctor.base;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhonghong.doctor.util.LogUtil;

/**
 * Created by guobin.zheng on 2015/7/22.
 * <p>
 * 简化的ACTIVITY 基类
 */
public abstract class MvcActivity extends BaseFragmentActivity {

    private final String TAG = "MvcActivity";

    public abstract void initView();

    public abstract void initData();

    public abstract void setListener();

    public abstract int getLayoutID();

    public LogUtil logUtil = LogUtil.getInstance(TAG);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        initView();
        initData();
        setListener();
    }


    public TextView getTextView(int nViewID) {
        View view = getView(nViewID);
        if (view instanceof TextView) {
            return (TextView) view;
        } else {
            logUtil.LogE("sb，这ID不是Textview");
            return new TextView(this);
        }
    }


    public <T extends View> T getView(int viewId) {
        return (T) this.findViewById(viewId);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        logUtil = null;
        super.onDestroy();
    }
}
