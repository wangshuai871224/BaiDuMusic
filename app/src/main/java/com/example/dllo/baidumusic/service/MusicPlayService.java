package com.example.dllo.baidumusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.dllo.baidumusic.bean.MusicItem;
import com.example.dllo.baidumusic.bean.MusicItemBean;
import com.example.dllo.baidumusic.bean.StateBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/7.
 */
public class MusicPlayService extends Service  {



    private MediaPlayer player;// 播放器对象
    private boolean isPlaying;   // 播放状态
    private String playUrl;    // 音乐文件路径
    private MusicItemBean bean;


    @Override
    public void onCreate() {
        super.onCreate();

        bean = new MusicItemBean();
        player = new MediaPlayer();
        // 只注册EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {

//        if (player != null && player.isPlaying()) {
//            player.stop();
//        }
//        player.release();
        // 取消注册
        EventBus.getDefault().unregister(this);
        // 以上释放完缓存之后, 在调用父类释放内存
        super.onDestroy();
    }

    @Subscribe
    public void getBean(MusicItemBean musicItemBean) {

        if (musicItemBean.getSongUrl() != null) {
            playUrl = musicItemBean.getSongUrl();
            info(playUrl);
        }
    }

    public void info(String url) {

        if (url != null) {
            try {

                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        //TODO 播放完被调用进行操作
                        player.stop();
                    }
                });
                if(player.isPlaying()){
                    player.stop();
                    player.reset();
                }
                player.setDataSource(MusicPlayService.this, Uri.parse(url));
                player.prepare();
                player.start();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//
//
////    暂停
//    public void pause() {
//        if (player.isPlaying() && player != null) {
//            player.pause();
//            isPlaying = false;
//        }
//
//    }
////    停止音乐
//    public void stop() {
//
//        if (player != null) {
//            player.stop();
//        }
//        try {
//            player.prepare();// 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    // 添加播放列表
//    public void addPlayList(MusicItem item) {
//
//    }
//
//    // 播放
//    public void play() {
//
//        // 可以清除以前播放器的状态
////                player.reset();
//        // 设置可以重复播放
////                player.setLooping(true);
//    }
//
//    // 播放下一首
//    public void playNext() {
//
//    }
//
//    // 播放上一首
//    public void palyPre() {
//
//    }
//
//    //将当前音乐播放的进度，拖动到指定的位置；
//    public void seekTo(int pos) {
//
//    }
//
//    public boolean isPlaying() {
//
//        return false;
//    }
//
//    // 获取当前正在播放的音乐的信息；
//    public MusicItem getCurrentItem() {
//        return null;
//    }



}
