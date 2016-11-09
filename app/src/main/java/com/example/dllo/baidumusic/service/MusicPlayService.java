package com.example.dllo.baidumusic.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dllo.baidumusic.MainActivity;
import com.example.dllo.baidumusic.bean.MusicItemBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/7.
 */
public class MusicPlayService extends Service  {


    private List<MusicItemBean> musicBeanList;
    // 定义播放器变量
    private MediaPlayer player;

    private String playUrl;

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        // 只注册EventBus
        musicBeanList = new ArrayList<>();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void getMusicBean(MusicItemBean musicBean) {
        //判断列表中是否已经存储过该音乐，如果存储过就不管它
        if (musicBeanList.contains(musicBean)){
            return;
        }
        //添加到播放列表的第一个位置
        musicBeanList.add(0, musicBean);
        //TODO 将信息(该音乐item)保存到数据库中

        playUrl =  musicBean.getSongUrl();
        info(playUrl);

    }

    public void info(String string) {

        if (string != null) {
            try {
                Uri songUrl = Uri.parse(playUrl);

                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        //TODO 播放完被调用进行操作
                        player.stop();
                    }
                });
//                 可以清除以前播放器的状态
                player.reset();
//                 设置可以重复播放
                player.setLooping(true);
                player.setDataSource(MusicPlayService.this, songUrl);
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


}
