package com.Models;

import com.google.gson.Gson;

/**
 * Created by nikhilagrawal on 25/08/16.
 */
public class CreatePlaylistRequest {

    private String mood;
//    private boolean isActive;
    private String playlistName;

    public CreatePlaylistRequest() {
    }

    public CreatePlaylistRequest(String artist,String playlistName) {
        this.mood = artist;
//        this.isActive = isActive;
        this.playlistName = playlistName;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }


    public static void main(String[] args) {
        CreatePlaylistRequest createPlaylistRequest = new CreatePlaylistRequest("AR rahman", "P1");
        System.out.println(new Gson().toJson(createPlaylistRequest));
    }
}
