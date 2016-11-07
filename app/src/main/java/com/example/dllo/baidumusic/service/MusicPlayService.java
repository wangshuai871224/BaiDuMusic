package com.example.dllo.baidumusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by dllo on 16/11/7.
 */
public class MusicPlayService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();

        try {

            Uri songUrl = Uri.parse("");
            MediaPlayer player = new MediaPlayer();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //TODO 播放完被调用进行操作
                }
            });
            player.setDataSource(this, songUrl);
            player.prepare();
            player.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
