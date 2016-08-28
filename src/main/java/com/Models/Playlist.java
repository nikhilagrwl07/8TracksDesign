package com.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by nikhilagrawal on 25/08/16.
 */

@Entity
@Table(name = "Playlist")
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Basic(optional = false)
    private int playListId;

    @Column(name = "playlistName")
    private String playlistName;

    @Column(name = "mood")
    private String mood;

    @Column(name = "is_active")
    private boolean isActive;

//    @ManyToOne(fetch = FetchType.LAZY)                                      // @NotFound(action = NotFoundAction.IGNORE)
//    @JoinColumn(name="song_id", referencedColumnName = "id")
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private SongDetails songDetails;


    public Playlist() {
    }

    public Playlist(String playlistName, String mood, boolean isActive) {
//        this.playListId = playListId;
        this.playlistName = playlistName;
        this.mood = mood;
        this.isActive = isActive;
//        this.songDetails = songDetails;
    }


    public int getPlayListId() {
        return playListId;
    }

    public void setPlayListId(int playListId) {
        this.playListId = playListId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }


}
