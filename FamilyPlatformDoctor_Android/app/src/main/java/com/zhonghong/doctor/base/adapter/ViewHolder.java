package com.zhonghong.doctor.base.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.zhonghong.doctor.util.UiUtils;

/**
 * Created by guobin.zheng on 2015/4/20
 *
 * ViewHolder 基类
 */
public class ViewHolder {

    private SparseArray<View> views;

    private View convertView;


    private ViewHolder(Context context, ViewGroup parent, int layoutId) {
        views = new SparseArray<>();
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        convertView.setTag(this);
    }

    public static ViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId);
        } else {
            return (ViewHolder) convertView.getTag();
        }
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }

        return (T) view;
    }

    public View getConvertView() {
        return convertView;
    }

    /**
     * 为TextView设置字符串
     *
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }


    /**
     * 设置View 显示状态
     *
     */
    public ViewHolder setVisibility(int viewId, int ViewStatus) {
        getView(viewId).setVisibility(ViewStatus);
        return this;
    }

    /**
     * 设置Uri
     *
     */
    public ViewHolder setFrascoUrl(int viewId, String url) {
        UiUtils.setImageToView(getView(viewId), url);
        return this;
    }


    /**
     * 设置图片
     *
     */
    public ViewHolder setFrascoUrl(int viewId, String url, int width, int height) {
        if (width == 0 || height == 0)
            return this;
        UiUtils.setImageToView(getView(viewId), url, width, height);
        return this;
    }
}

