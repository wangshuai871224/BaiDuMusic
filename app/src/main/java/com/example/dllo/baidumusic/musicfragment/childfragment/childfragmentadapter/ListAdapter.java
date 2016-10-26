package com.example.dllo.baidumusic.musicfragment.childfragment.childfragmentadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/25.
 */
public class ListAdapter extends BaseAdapter{
    Context mContext;
    ArrayList<String> strings;

    public ListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int i) {
        return strings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
            viewHolder = new ListViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ListViewHolder) view.getTag();
        }

        return view;
    }

    class ListViewHolder {

        private final ImageView image;

        public ListViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.list_image);
        }
    }
}
