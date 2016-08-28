package com.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * Created by nikhilagrawal on 25/08/16.
 */

@Entity
@Table(name = "PlayListIdToTagId")
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayListIdToTagId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Basic(optional = false)
    private int id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tag", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Tag tagId;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="playList", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Playlist playListId;


    public PlayListIdToTagId(Tag tagId, Playlist playlists) {
//        this.id = id;
        this.tagId = tagId;
        this.playListId = playlists;
    }

    public PlayListIdToTagId() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tag getTagId() {
        return tagId;
    }

    public void setTagId(Tag tagId) {
        this.tagId = tagId;
    }

    public Playlist getPlaylists() {
        return playListId;
    }

    public void setPlaylists(Playlist playlists) {
        this.playListId = playlists;
    }
}
