package com.example.dllo.baidumusic.bean;

/**
 * Created by dllo on 16/11/10.
 */
public class MusicItem {

    //重写MusicItem的equals()方法
    @Override
    public boolean equals(Object o) {
        MusicItem another = (MusicItem) o;

        //音乐的Uri相同，则说明两者相同
//        return another.songUri.equals(this.songUri);
        return true;
    }
}
