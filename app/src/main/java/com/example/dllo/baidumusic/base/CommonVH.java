package com.example.dllo.baidumusic.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.tools.VolleySingleton;

/**
 * Created by WangShuai on 16/10/31.
 * 通用的ViewHolder
 */
public class CommonVH extends RecyclerView.ViewHolder{
    // SparesArray 用法和HashMap一样
    // 但是key 固定是int类型
    // 用来存放所有的view, key就是view的id
    private SparseArray<View> views;
    private View itemView;// 行布局

    public CommonVH(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<>();
    }

    /**
     * 通过View的id来获得指定的View
     * 如果View没有复制, 就先执行findViewByID
     * 然后放到集合里, 使用泛型来取消强转
     * @param id
     * @return 指定View
     */
    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            // 证明SparesArray里没有这个view
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (T)view;
    }


    // 静态 专门给ListView使用的方法
    public static CommonVH getViewHolder(View itemView, ViewGroup parent, int itemId) {
        CommonVH commonVH;
        if (itemView == null) {
            // 获取Context对象
            Context context = parent.getContext();
            itemView = LayoutInflater.from(context).inflate(itemId, parent, false);
            commonVH = new CommonVH(itemView);
            itemView.setTag(commonVH);
        }else {
            commonVH = (CommonVH) itemView.getTag();
        }
        return  commonVH;
    }

    // 专门给RecyclerView使用的方法
    public static CommonVH getViewHolder(ViewGroup parent, int itemId) {

        return  getViewHolder(null, parent, itemId);
    }

    // 返回行布局
    public  View getItemView() {
        return  itemView;
    }

    /**********ViewHolder 设置数据的方法**********/
    public CommonVH setText(int id, String text) {
        TextView textView = getView(id);
        textView.setText(text);
        return this;
    }

    public CommonVH setImage(int id, int imgId) {
        ImageView imageView = getView(id);
        imageView.setImageResource(imgId);
        return this;// 返回本类对象
    }

    public CommonVH setImage(int id, String url) {
        ImageView imageView = getView(id);
        VolleySingleton.getInstance().getImage(url, imageView);
        //TODO 网络请求图片
        return this;
    }

    public CommonVH setViewClick(int id, View.OnClickListener listener) {

        getView(id).setOnClickListener(listener);

        return this;
    }

    public CommonVH setItemClick(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
        return this;
    }
}
