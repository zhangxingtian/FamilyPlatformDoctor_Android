package com.zhonghong.doctor.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by guobin.zheng on 2015/4/20.
 *
 * adapter 基类
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context context;
    protected List<T> datas;
    protected final int layoutId;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        this.layoutId = layoutId;
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public T getItem(int position) {
        if (datas == null)
            throw new NullPointerException("CommonAdapter dates is null");
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.getViewHolder(context, convertView, parent, layoutId);
        convertView(viewHolder, datas.get(position), position);
        return viewHolder.getConvertView();
    }

    public abstract void convertView(ViewHolder viewHolder, T data, int position);

}
