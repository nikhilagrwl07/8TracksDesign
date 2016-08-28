package com.Service;

import com.DAO.*;
import com.Models.*;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nikhilagrawal on 25/08/16.
 */
public class SwiggyPlayService {


    private final static Logger logger = LoggerFactory.getLogger(SwiggyPlayService.class);
    private SongDAO songDAO;
    private TagDAO tagDAO;
    private PlatyListDAO platyListDAO;
    private PlayListIdToTagIdDAO playListIdToTagIdDAO;

    @Inject
    public SwiggyPlayService(SongDAO songDAO, TagDAO tagDAO, PlatyListDAO platyListDAO, PlayListIdToTagIdDAO playListIdToTagIdDAO) {
        this.songDAO = songDAO;
        this.tagDAO = tagDAO;
        this.platyListDAO = platyListDAO;
        this.playListIdToTagIdDAO = playListIdToTagIdDAO;
    }

    public void addPlayList(CreatePlaylistRequest createPlaylistRequest)
    {
        Playlist playlist = new Playlist(createPlaylistRequest.getPlaylistName(),createPlaylistRequest.getMood(),true);
//        Customer customer=new Customer(createUserModel.getCustomername(), DateTime.now(),DateTime.now(),true , createUserModel.getCustomerEmailId());
        platyListDAO.persist(playlist);
    }


    public void addSongsInPlayList(AddSongsInPlaylistRequest addSongsInPlaylistRequest)
    {
        Playlist playlist  =   platyListDAO.getplayListFromplaylistName(addSongsInPlaylistRequest.getPlaylistName());

        SongDetails songDetails = new SongDetails(addSongsInPlaylistRequest.getSongname(),addSongsInPlaylistRequest.getArtist(),playlist);

//        Customer customer=new Customer(createUserModel.getCustomername(), DateTime.now(),DateTime.now(),true , createUserModel.getCustomerEmailId());
        songDAO.persist(songDetails);
    }


    public void addTag(String tagName)
    {
        Tag tag = new Tag();
        tag.setTagData(tagName);
//        Customer customer=new Customer(createUserModel.getCustomername(), DateTime.now(),DateTime.now(),true , createUserModel.getCustomerEmailId());
        tagDAO.persist(tag);
    }

    public void assciateTagtoPlaylist(String playListName,String tagName)
    {

        Tag tag  = tagDAO.getTagDetails(tagName);
        Playlist playlist  =   platyListDAO.getplayListFromplaylistName(playListName);
        PlayListIdToTagId playListIdToTagId = new PlayListIdToTagId(tag,playlist);
//        Customer customer=new Customer(createUserModel.getCustomername(), DateTime.now(),DateTime.now(),true , createUserModel.getCustomerEmailId());
        playListIdToTagIdDAO.persist(playListIdToTagId);
    }


    public List<RetrunModel> explore(ExploreRequest exploreRequest)
    {
        List<RetrunModel> retrunModel = new ArrayList<>();

           HashMap<Integer , List<SongDetails>> map = new HashMap<>();
        for(String tags : exploreRequest.getTags())
        {
            List<SongDetails> songDetails = new ArrayList<>();
            Tag tag  = tagDAO.getTagDetails(tags);
            List<PlayListIdToTagId> playListIdToTagId  = playListIdToTagIdDAO.getTagDetails(tag);

            for(PlayListIdToTagId playListIdToTag : playListIdToTagId) {
                songDetails = songDAO.getSongDetailsFromPlayListId(playListIdToTag.getPlaylists());
                if(map.containsKey(playListIdToTag.getPlaylists().getPlayListId())){
                    List<SongDetails> songDetailses = map.get(playListIdToTag.getPlaylists().getPlayListId());
                    for(SongDetails songDetails1 :songDetails){
                        songDetailses.add(songDetails1);
                    }
                    map.put(playListIdToTag.getPlaylists().getPlayListId(),songDetailses);
                }
                else {
                    map.put(playListIdToTag.getPlaylists().getPlayListId(), songDetails);
                }

            }

        }


        for (Map.Entry<Integer , List<SongDetails>> entry : map.entrySet())
        {
            RetrunModel retrunModel1 = new RetrunModel(entry.getKey(),entry.getValue());
            retrunModel.add(retrunModel1);
        }

        return retrunModel;
    }

}
