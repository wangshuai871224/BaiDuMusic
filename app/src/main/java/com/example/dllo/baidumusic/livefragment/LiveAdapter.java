package com.example.dllo.baidumusic.livefragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/25.
 */
public class LiveAdapter extends RecyclerView.Adapter{

    Context mContext;
    ArrayList<String> stringBean;

    public LiveAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setStringBean(ArrayList<String> stringBean) {
        this.stringBean = stringBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false);
        LiveViewHolder viewHolder = new LiveViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LiveViewHolder viewHolder = (LiveViewHolder) holder;

    }

    @Override
    public int getItemCount() {
        return stringBean.size();
    }

    class LiveViewHolder extends RecyclerView.ViewHolder {


        public LiveViewHolder(View itemView) {
            super(itemView);

        }
    }
}
