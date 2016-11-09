package com.example.dllo.baidumusic.bean;

/**
 * Created by dllo on 16/11/8.
 */
public class ControlState {



    public void addPlayList() {
        // 参数:MusicItem item
        // 方法:addPlayListInner(item)
    }

    public void play() {
        playInner();
    }

    public void playNext() {
        playNextInner();
    }

    public void palyPre() {
        playPreInner();
    }

    public void pause() {
        pauseInner();
    }

    public void seekTo(int pos) {
        seekToInner(pos);
    }

    public boolean isPlaying() {
        return isPlayingInner();
    }

//    public MusicItem getCurrentItem() {
//        return getCurrentItemInner();
//    }
//
//    public MusicItem getCurrentItemInner() {
//        return null;
//    }


    public void seekToInner(int pos) {
    }

    public void pauseInner() {
    }

    public void playPreInner() {

    }

    public void playInner() {

    }
    public void playNextInner() {

    }

    public boolean isPlayingInner() {
        return false;
    }
}
