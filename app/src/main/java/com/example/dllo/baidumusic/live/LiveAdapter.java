package com.example.dllo.baidumusic.live;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.bean.LiveBean;
import com.example.dllo.baidumusic.tools.VolleySingleton;

/**
 * Created by dllo on 16/10/25.
 */
public class LiveAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private LiveBean beans;

    public LiveAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setBeans(LiveBean beans) {
        this.beans = beans;
        notifyDataSetChanged();
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
        viewHolder.nickName.setText(beans.getData().getData().get(position).getNickname());
        viewHolder.userCount.setText(String.valueOf(beans.getData().getData().get(position).getUsercount()));
        VolleySingleton.getInstance().getImage(beans.getData().getData().get(position).getLiveimg()
                , viewHolder.image);

    }

    @Override
    public int getItemCount() {
        return beans == null ? 0 :beans.getData().getData().size();
    }

    class LiveViewHolder extends RecyclerView.ViewHolder {


        private TextView nickName;
        private TextView userCount;
        private ImageView image;

        public LiveViewHolder(View itemView) {
            super(itemView);
            nickName = (TextView) itemView.findViewById(R.id.nick_name);
            userCount = (TextView) itemView.findViewById(R.id.live_num);
            image = (ImageView) itemView.findViewById(R.id.live_img);

        }
    }
}
