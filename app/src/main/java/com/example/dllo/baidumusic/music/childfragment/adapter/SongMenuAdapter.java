package com.example.dllo.baidumusic.music.childfragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.MainActivity;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.CommonVH;
import com.example.dllo.baidumusic.bean.SongMenuBean;
import com.example.dllo.baidumusic.tools.VolleySingleton;

/**
 * Created by dllo on 16/10/26.
 */
public class SongMenuAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    Context mContext;
    SongMenuBean bean;

    public SongMenuAdapter(Context mContext) {
        this.mContext = mContext;
    }

    /**
     *
     * @param bean
     * @param isRefresh true 代表 是新的数据 替换原来的 Item显示最新的且条数不变
     *                  false 代表 保留原来的数据,把新的数据添加到原来的数据集合里
     */
    public void setBean(SongMenuBean bean,boolean isRefresh){

        if(isRefresh || this.bean == null){
            setBean(bean);
        }else {
            this.bean.getDiyInfo().addAll(bean.getDiyInfo());
            notifyDataSetChanged();
        }
    }

    public void setBean(SongMenuBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CommonVH viewHolder = CommonVH.getViewHolder(parent,R.layout.item_song);
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_song, parent, false);
//        SongListViewHolder viewHolder = new SongListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        CommonVH commonVH = (CommonVH) holder;
        commonVH.setText(R.id.listen_num, String.valueOf(bean.getDiyInfo().get(position).getListen_num()))
                .setText(R.id.user_name, bean.getDiyInfo().get(position).getUsername())
                .setText(R.id.song_title, bean.getDiyInfo().get(position).getTitle())
                .setImage(R.id.song_image,bean.getDiyInfo().get(position).getList_pic());

        commonVH.setViewClick(R.id.song_play, this)
                .setItemClick(this);
//        SongListViewHolder songListViewHolder = (SongListViewHolder) holder;
//        VolleySingleton.getInstance().getImage(bean.getDiyInfo().get(position).getList_pic(), songListViewHolder.songImage);
//        // 获取到的是int类型,需要转化成string
//        songListViewHolder.listenNum.setText(String.valueOf(bean.getDiyInfo().get(position).getListen_num()));
//        songListViewHolder.userName.setText(bean.getDiyInfo().get(position).getUsername());
//        songListViewHolder.songTitle.setText(bean.getDiyInfo().get(position).getTitle());
    }

    @Override
    public int getItemCount() {

        return bean == null ? 0 : bean.getDiyInfo().size();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.song_menu_item:

                break;
            case R.id.song_play:
                Intent intent1 = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent1);
                break;
        }
    }

//    class SongListViewHolder extends RecyclerView.ViewHolder{
//
//
//        private ImageView songImage;
//        private TextView listenNum;
//        private TextView userName;
//        private TextView songTitle;
//
//        public SongListViewHolder(View itemView) {
//            super(itemView);
//
//            songImage = (ImageView) itemView.findViewById(R.id.song_image);
//            listenNum = (TextView) itemView.findViewById(R.id.listen_num);
//            userName = (TextView) itemView.findViewById(R.id.user_name);
//            songTitle = (TextView) itemView.findViewById(R.id.song_title);
//
//        }
//    }
}
