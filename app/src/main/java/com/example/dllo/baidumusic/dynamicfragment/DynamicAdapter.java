package com.example.dllo.baidumusic.dynamicfragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.baidumusic.fragmentclass.DynamicBean;

/**
 * Created by dllo on 16/10/28.
 */
public class DynamicAdapter extends BaseAdapter{
    Context mContext;
    DynamicBean beans;
    public DynamicAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setBeans(DynamicBean beans) {
        this.beans = beans;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
