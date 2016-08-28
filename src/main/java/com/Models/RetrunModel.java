package com.Models;

import java.util.List;

/**
 * Created by nikhilagrawal on 25/08/16.
 */
public class RetrunModel {

    int playListid;
    List<SongDetails> songDetails;

    public RetrunModel(int playListid, List<SongDetails> songDetails) {
        this.playListid = playListid;
        this.songDetails = songDetails;
    }

    public RetrunModel() {
    }

    public int getPlayListid() {
        return playListid;
    }

    public void setPlayListid(int playListid) {
        this.playListid = playListid;
    }

    public List<SongDetails> getSongDetails() {
        return songDetails;
    }

    public void setSongDetails(List<SongDetails> songDetails) {
        this.songDetails = songDetails;
    }
}
