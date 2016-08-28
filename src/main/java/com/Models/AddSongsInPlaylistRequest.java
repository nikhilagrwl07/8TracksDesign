package com.Models;

import com.google.gson.Gson;

/**
 * Created by nikhilagrawal on 25/08/16.
 */
public class AddSongsInPlaylistRequest {

    private String playlistName;
    private String artist;
    private String songname;

    public AddSongsInPlaylistRequest(String playlistName,String artist, String songname) {
        this.playlistName = playlistName;
        this.artist = artist;
        this.songname = songname;
    }

    public AddSongsInPlaylistRequest() {
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public static void main(String[] args) {
        AddSongsInPlaylistRequest addSongsInPlaylistRequest = new AddSongsInPlaylistRequest("P1","A1","S1");

        System.out.println(new Gson().toJson(addSongsInPlaylistRequest));
    }
}
