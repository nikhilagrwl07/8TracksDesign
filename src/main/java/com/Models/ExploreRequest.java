package com.Models;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhilagrawal on 25/08/16.
 */
public class ExploreRequest {
    private List<String> tags;

    public ExploreRequest(List<String> tags) {
        this.tags = tags;
    }

    public ExploreRequest() {
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public static void main(String[] args) {
        ExploreRequest exploreRequest = new ExploreRequest();
         List<String> tags = new ArrayList<>();
        tags.add("T1");
        tags.add("T2");
        tags.add("T3");
        exploreRequest.setTags(tags);
        System.out.println(new Gson().toJson(exploreRequest));
    }
}
