package com.example.dllo.baidumusic.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.CommonVH;
import com.example.dllo.baidumusic.bean.ContentBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/7.
 */
public class PlayListContentAdapter extends BaseAdapter{
    ArrayList<ContentBean> beans;

    public void setBeans(ArrayList<ContentBean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans == null ? 0 : beans.size();
    }

    @Override
    public Object getItem(int i) {
        return beans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonVH commonVH = CommonVH.getViewHolder(view, viewGroup, R.layout.content_list);
        commonVH.setText(R.id.content_title, beans.get(i).getTitle());
        commonVH.setText(R.id.content_author, beans.get(i).getAuthor());

        return commonVH.getItemView();
    }
}
