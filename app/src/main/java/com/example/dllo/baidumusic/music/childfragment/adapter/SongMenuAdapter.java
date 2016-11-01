package com.example.dllo.baidumusic.music.childfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.bean.SongMenuBean;
import com.example.dllo.baidumusic.tools.VolleySingleton;

/**
 * Created by dllo on 16/10/26.
 */
public class SongMenuAdapter extends RecyclerView.Adapter{
    Context mContext;
    SongMenuBean bean;

    public SongMenuAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setBean(SongMenuBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_song, parent, false);
        SongListViewHolder viewHolder = new SongListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        SongListViewHolder songListViewHolder = (SongListViewHolder) holder;
        VolleySingleton.getInstance().getImage(bean.getDiyInfo().get(position).getList_pic(), songListViewHolder.songImage);
        // 获取到的是int类型,需要转化成string
        songListViewHolder.listenNum.setText(String.valueOf(bean.getDiyInfo().get(position).getListen_num()));
        songListViewHolder.userName.setText(bean.getDiyInfo().get(position).getUsername());
        songListViewHolder.songTitle.setText(bean.getDiyInfo().get(position).getTitle());
    }

    @Override
    public int getItemCount() {

        return bean == null ? 0 : bean.getDiyInfo().size();
    }

    class SongListViewHolder extends RecyclerView.ViewHolder{


        private ImageView songImage;
        private TextView listenNum;
        private TextView userName;
        private TextView songTitle;

        public SongListViewHolder(View itemView) {
            super(itemView);

            songImage = (ImageView) itemView.findViewById(R.id.song_image);
            listenNum = (TextView) itemView.findViewById(R.id.listen_num);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            songTitle = (TextView) itemView.findViewById(R.id.song_title);

        }
    }
}
