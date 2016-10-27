package com.example.dllo.baidumusic.musicfragment.childfragment.childfragmentadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.musicfragment.childfragment.childactivity.NewSongActivity;
import com.example.dllo.baidumusic.musicfragment.childfragment.childragmentclass.ListBean;
import com.example.dllo.baidumusic.tools.VolleySingleton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/25.
 */
public class ListAdapter extends BaseAdapter{
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
        ListViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
            viewHolder = new ListViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ListViewHolder) view.getTag();
        }
        VolleySingleton.getInstance().getImage(bean.getContent().get(i).getPic_s192(),viewHolder.image);
        viewHolder.news.setText(bean.getContent().get(i).getName());
        viewHolder.firstTitle.setText(bean.getContent().get(i).getContent().get(0).getTitle());
        viewHolder.firstAuthor.setText(bean.getContent().get(i).getContent().get(0).getAuthor());
        viewHolder.secondTitle.setText(bean.getContent().get(i).getContent().get(1).getTitle());
        viewHolder.secondAuthor.setText(bean.getContent().get(i).getContent().get(1).getAuthor());
        viewHolder.thirdTitle.setText(bean.getContent().get(i).getContent().get(2).getTitle());
        viewHolder.thirdAuthor.setText(bean.getContent().get(i).getContent().get(2).getAuthor());

        viewHolder.imagePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        viewHolder.listLL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, NewSongActivity.class);
////                intent.putExtra("parameter", bean.getContent().get(i));
//                mContext.startActivity(intent);
//            }
//        });



        return view;
    }

    class ListViewHolder {

        private final ImageView image;
        private final TextView news;
        private final TextView firstTitle;
        private final TextView firstAuthor;
        private final TextView secondTitle;
        private final TextView secondAuthor;
        private final TextView thirdTitle;
        private final TextView thirdAuthor;
        private final ImageView imagePlay;
        private final LinearLayout listLL;

        public ListViewHolder(View view) {
            listLL = (LinearLayout) view.findViewById(R.id.list_item_ll);
            imagePlay = (ImageView) view.findViewById(R.id.image_play);
            image = (ImageView) view.findViewById(R.id.list_image);
            news = (TextView) view.findViewById(R.id.new_announcement);
            firstTitle = (TextView) view.findViewById(R.id.first_title);
            firstAuthor = (TextView) view.findViewById(R.id.first_author);
            secondTitle = (TextView) view.findViewById(R.id.second_title);
            secondAuthor = (TextView) view.findViewById(R.id.second_author);
            thirdTitle = (TextView) view.findViewById(R.id.third_title);
            thirdAuthor = (TextView) view.findViewById(R.id.third_author);
        }
    }
}
