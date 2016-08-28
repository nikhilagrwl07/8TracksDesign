package com.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * Created by nikhilagrawal on 25/08/16.
 */

@Entity
@AllArgsConstructor
@Table(name = "SongDetails")
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SongDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Basic(optional = false)
    private int songId;

    @Column(name = "songname")
    private String songname;

    @Column(name = "artist")
    private String artist;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)                                      // @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="play_list_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Playlist playlists;


    public SongDetails() {
    }

    public SongDetails(String songname, String artist, Playlist playlists) {
//        this.songId = songId;
        this.songname = songname;
        this.artist = artist;
//        this.isActive = isActive;
        this.playlists = playlists;
    }


    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Playlist getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist playlists) {
        this.playlists = playlists;
    }
}
