package com.zhonghong.doctor.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhonghong.doctor.util.LogUtil;

/**
 * Created by guobin.zheng on 2015/7/22.
 *
 * 简化的FRAGMENT 基类
 */
public abstract class MvcFragment extends BaseFragment {

    private final String TAG = "MvcFragment";

    public abstract int getLayoutID();

    public abstract void initView();

    public abstract void initData();

    public abstract void setListener();

    public abstract void releaseRes();

    LogUtil logUtil = LogUtil.getInstance(TAG);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = getView(getLayoutID(), container);
        initView();
        setListener();
        return contentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initData();
        super.onActivityCreated(savedInstanceState);
    }

    private View getView(int layoutID, ViewGroup container) {
        if (layoutID == 0) {
            LogUtil.getInstance(TAG).LogE("sb，请输入layoutID");
            return null;
        }
        return LayoutInflater.from(mContext).inflate(layoutID, container, false);
    }
    public TextView getTextView(int nViewID) {
        View view = getView(nViewID);
        if (view instanceof TextView) {
            return (TextView) view;
        } else {
            logUtil.LogE("sb，这ID的View不是Textview");
            return new TextView(mContext);
        }
    }

    public Button getButton(int nViewID) {
        View view = getView(nViewID);
        if (view instanceof Button) {
            return (Button) view;
        } else {
            logUtil.LogE("sb，这ID的View不是Button");
            return new Button(mContext);
        }
    }

    public SimpleDraweeView getSimpleDraweeView(int nViewID) {
        View view = getView(nViewID);
        if (view instanceof SimpleDraweeView) {
            return (SimpleDraweeView) view;
        } else {
            logUtil.LogE("sb，这ID的View不是SimpleDraweeView");
            return new SimpleDraweeView(mContext);
        }
    }

    public <T extends View> T getView(int viewId) {
        if (contentView == null) {
            logUtil.LogE("sb，contentView还没有初始化");
            return (T) new View(mContext);
        }
        return (T) contentView.findViewById(viewId);
    }

    @Override
    public void onDetach() {
        logUtil = null;
        releaseRes();
        super.onDetach();
    }



}
