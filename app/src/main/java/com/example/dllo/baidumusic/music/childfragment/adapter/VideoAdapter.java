package com.example.dllo.baidumusic.music.childfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.bean.VideoBean;
import com.example.dllo.baidumusic.tools.VolleySingleton;

/**
 * Created by dllo on 16/10/27.
 */
public class VideoAdapter extends RecyclerView.Adapter{
    Context mContext;
    VideoBean bean;

    public VideoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setBean(VideoBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
        VideoViewHolder viewHolder = new VideoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
        VolleySingleton.getInstance().getImage(
                bean.getResult().getMv_list().get(position).getThumbnail(), videoViewHolder.image);
        videoViewHolder.videoTitle.setText(
                bean.getResult().getMv_list().get(position).getTitle());
        videoViewHolder.videoArtist.setText(
                bean.getResult().getMv_list().get(position).getArtist());
    }

    @Override
    public int getItemCount() {

        return bean == null ? 0 : bean.getResult().getMv_list().size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView videoArtist;
        private TextView videoTitle;

        public VideoViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.video_image);
            videoTitle = (TextView) itemView.findViewById(R.id.video_name);
            videoArtist = (TextView) itemView.findViewById(R.id.video_title);
        }
    }
}
