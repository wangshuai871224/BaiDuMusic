package com.example.dllo.baidumusic.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.CommonVH;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/3.
 */
public class PlayListAdapter extends BaseAdapter{
    ArrayList<String> arrayList;

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonVH commonVH = CommonVH.getViewHolder(view, viewGroup, R.layout.item_paly_list);
        commonVH.setText(R.id.paly_list_people, arrayList.get(i));

        return commonVH.getItemView();
    }
}
