package com.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;

import javax.persistence.*;

/**
 * Created by nikhilagrawal on 25/08/16.
 */

@Entity
@Table(name = "Tag")
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Basic(optional = false)
    private int tagId;


    @Column(name = "tagData")
    private String tagData;

    public Tag() {
    }

    public Tag(int tagId, String tagData) {
        this.tagId = tagId;
        this.tagData = tagData;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagData() {
        return tagData;
    }

    public void setTagData(String tagData) {
        this.tagData = tagData;
    }
}
