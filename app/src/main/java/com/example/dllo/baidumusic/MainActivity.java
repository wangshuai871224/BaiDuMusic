package com.example.dllo.baidumusic;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.baidumusic.adapter.MainAdapter;
import com.example.dllo.baidumusic.base.BaseActivity;
import com.example.dllo.baidumusic.bean.MusicItemBean;
import com.example.dllo.baidumusic.bean.PlayBean;
import com.example.dllo.baidumusic.bean.StateBean;
import com.example.dllo.baidumusic.dynamic.DynamicFragment;
import com.example.dllo.baidumusic.events.ControlEvent;
import com.example.dllo.baidumusic.fragment.FragmentSet;
import com.example.dllo.baidumusic.fragment.PlayListFragment;
import com.example.dllo.baidumusic.listener.ReplaceListener;
import com.example.dllo.baidumusic.live.LiveFragment;
import com.example.dllo.baidumusic.mine.MineFragment;
import com.example.dllo.baidumusic.music.MusicFragment;
import com.example.dllo.baidumusic.service.MusicPlayService;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener,ReplaceListener{

    private ImageView imageView;
    private TextView playNmae;
    private ImageView set;
    private ImageView query;
    private ViewPager mainVP;
    private TabLayout mainTL;
    private ImageView play;
    private ImageView next;
    private ImageView playList;
    private LinearLayout mainLL;
    private ArrayList<Fragment> fragments;
    private MainAdapter adapter;
    private FragmentManager manager;
    private PlayListFragment playListFragment;
    private boolean isVisible = true;
    private MusicFragment musicFragment;


    @Override
    protected void initData() {

        EventBus.getDefault().register(this);
        adapter = new MainAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();

        musicFragment = new MusicFragment();
        fragments.add(new MineFragment());
        fragments.add(musicFragment);
        fragments.add(new DynamicFragment());
        fragments.add(new LiveFragment());

        adapter.setFragmentBean(fragments);
        mainVP.setAdapter(adapter);
        mainTL.setupWithViewPager(mainVP);



    }

    @Override
    protected void initViews() {



        mainLL = bindView(R.id.main_ll);
        mainVP = bindView(R.id.main_vp);
        mainTL = bindView(R.id.main_tb);
        set = bindView(R.id.main_set);
        query = bindView(R.id.main_query);
        imageView = bindView(R.id.main_image);
        playNmae = bindView(R.id.play_name);
        play = bindView(R.id.main_play);
        next = bindView(R.id.main_next);
        playList = bindView(R.id.main_playList);

        playListFragment = new PlayListFragment();
        manager = getSupportFragmentManager();
        setClick(this, mainLL, set, query, imageView, play, next, playList);

    }


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();

        switch (view.getId()) {
            case R.id.main_set:
                transaction.replace(R.id.replace_fragment, new FragmentSet());
                transaction.commit();
                break;
            case R.id.main_query:
                break;
            case R.id.main_image:
                break;
            case R.id.main_play:

                ControlEvent controlEvent = new ControlEvent(ControlEvent.PLAY_OR_STOP);
                EventBus.getDefault().post(controlEvent);

                break;
            case R.id.main_next:
                ControlEvent controlEvent1 = new ControlEvent(ControlEvent.PLAY_NEXT);
                EventBus.getDefault().post(controlEvent1);
                break;
            case R.id.main_playList:
                playList();
                break;
            case R.id.main_ll:
                mainLL.getBackground().setAlpha(1 / 2);
                break;
        }
    }

    // 接收从服务里穿过来的状态
    @Subscribe
    public void state(StateBean stateBean) {
        int state = stateBean.getState();
        switch (state) {
            case StateBean.PAUSE_PICTURE:
                play.setImageResource(R.mipmap.bt_minibar_pause_normal);
                break;
            case StateBean.PLAY_PICTURE:
                play.setImageResource(R.mipmap.bt_minibar_play_normal);
                break;

        }
    }


    public void playList(){
        FragmentTransaction transaction = manager.beginTransaction();
        if (isVisible) {
            transaction.add(R.id.replace_fragment, playListFragment);
        } else {
            transaction.remove(playListFragment);
        }
        isVisible = !isVisible;
        transaction.commit();
    }

    // 实现接口  implements
    @Override
    public void onReplace(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(musicFragment);
        transaction.add(R.id.replace_fragment, fragment).addToBackStack(null);
        transaction.commit();
    }
}
