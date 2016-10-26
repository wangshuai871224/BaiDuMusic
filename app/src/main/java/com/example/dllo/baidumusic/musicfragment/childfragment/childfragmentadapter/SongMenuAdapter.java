package com.example.dllo.baidumusic.musicfragment.childfragment.childfragmentadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/26.
 */
public class SongMenuAdapter extends RecyclerView.Adapter{
    Context mContext;
    ArrayList<String> arrayList;

    public SongMenuAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.song_item, parent, false);
        SongListViewHolder viewHolder = new SongListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        SongListViewHolder songListViewHolder = (SongListViewHolder) holder;
        songListViewHolder.songName.setText(arrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SongListViewHolder extends RecyclerView.ViewHolder{

        private final TextView songName;

        public SongListViewHolder(View itemView) {
            super(itemView);
            songName = (TextView) itemView.findViewById(R.id.song_name);
        }
    }
}
