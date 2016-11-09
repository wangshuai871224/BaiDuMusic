package com.example.dllo.baidumusic.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Binder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.adapter.PlayListContentAdapter;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.bean.ContentBean;
import com.example.dllo.baidumusic.bean.EventBusBean;
import com.example.dllo.baidumusic.bean.MusicListChildBean;
import com.example.dllo.baidumusic.bean.SongMenuBean;
import com.example.dllo.baidumusic.music.childfragment.SongMenuFragment;
import com.example.dllo.baidumusic.tools.GsonRequest;
import com.example.dllo.baidumusic.tools.URLValues;
import com.example.dllo.baidumusic.tools.VolleySingleton;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.GenericArrayType;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by dllo on 16/11/5.
 */
public class PlayFragment extends BaseFragment{

    private ImageView imageView;
    private ListView playList;
    private TextView title;
    private TextView listeNum;
    private TextView desc;
    private String url;
    private TextView startNum;
    private TextView playNum;
    private TextView tag;
    private PlayListContentAdapter adapter;
    private ArrayList<ContentBean> arrayList;



    public PlayFragment() {

    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        Log.d("Sysout","注册");

    }

    @Override
    protected void initView() {
        imageView = bindView(R.id.small_background_image);

        playList = bindView(R.id.list_content_play);
        title = bindView(R.id.play_title);
        listeNum = bindView(R.id.play_list_num);
        desc = bindView(R.id.play_desc);
        startNum = bindView(R.id.start_collect_num);
        playNum = bindView(R.id.play_num);
        tag = bindView(R.id.lable_tag);
        adapter = new PlayListContentAdapter();
        arrayList = new ArrayList<>();


    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_paly;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    // 粘性, 先发送后注册的时候EventBus发送的时候需要sticky
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void eventBusBean(EventBusBean eventBusBean) {
        url = URLValues.MUSIC_PLAYLIST_LIST_FRONT + eventBusBean.getUrl()
                + URLValues.MUSIC_PLAYLIST_LIST_BEHIND;
        Log.d("PlayFragment", url);
        commond();
    }

    public void commond() {
        GsonRequest<MusicListChildBean> gsonRequest = new GsonRequest<MusicListChildBean>(MusicListChildBean.class
                , url, new Response.Listener<MusicListChildBean>() {
            @Override
            public void onResponse(MusicListChildBean response) {
                title.setText(response.getTitle());
                desc.setText(response.getDesc());
                listeNum.setText(String.valueOf(response.getListenum()));
                startNum.setText(String.valueOf(response.getCollectnum()));
                playNum.setText("/" + response.getContent().size() + "首歌");
                tag.setText(response.getTag());
                Picasso.with(mContext).load(response.getPic_300()).into(imageView);
                for (int i = 0; i < response.getContent().size(); i++) {
                    ContentBean bean = new ContentBean();
                    bean.setTitle(response.getContent().get(i).getTitle());
                    bean.setAuthor(response.getContent().get(i).getAuthor());
                    bean.setSongId(response.getContent().get(i).getSong_id());
                    arrayList.add(bean);
                }
                adapter.setBeans(arrayList);
                playList.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance().addRequest(gsonRequest);

    }
}
