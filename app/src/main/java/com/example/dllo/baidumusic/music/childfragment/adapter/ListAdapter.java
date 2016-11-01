package com.example.dllo.baidumusic.music.childfragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.CommonVH;
import com.example.dllo.baidumusic.bean.ListBean;
import com.example.dllo.baidumusic.tools.VolleySingleton;

/**
 * Created by dllo on 16/10/25.
 */
public class ListAdapter extends BaseAdapter implements View.OnClickListener {
    Context mContext;
    ListBean bean;

    public ListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setBean(ListBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getContent().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getContent().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        CommonVH viewHolder = CommonVH.getViewHolder(view, viewGroup, R.layout.item_list);

        viewHolder.setImage(R.id.list_image, bean.getContent().get(i).getPic_s192());
        viewHolder.setText(R.id.new_announcement,bean.getContent().get(i).getName());

        viewHolder.setText(R.id.first_title,bean.getContent().get(i).getContent().get(0).getTitle())
                .setText(R.id.first_author,bean.getContent().get(i).getContent().get(0).getAuthor());
        viewHolder.setText(R.id.second_title,bean.getContent().get(i).getContent().get(1).getTitle())
                .setText(R.id.second_author,bean.getContent().get(i).getContent().get(1).getAuthor());
        viewHolder.setText(R.id.third_title,bean.getContent().get(i).getContent().get(2).getTitle())
                .setText(R.id.third_author,bean.getContent().get(i).getContent().get(2).getAuthor());
        viewHolder.setViewClick(R.id.new_announcement, this)
                  .setViewClick(R.id.image_play, this);

        return viewHolder.getItemView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_announcement:
                break;
            case R.id.image_play:
                break;
        }
    }
}
