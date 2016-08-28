package com.DAO;

import com.Models.*;
import com.Util.HSession;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhilagrawal on 25/08/16.
 */
public class SongDAO extends AbstractDAO<SongDetails> {

    private final static Logger logger = LoggerFactory.getLogger(SongDAO.class);
    private SessionFactory sessionFactory;

    @Inject
    public SongDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory=sessionFactory;

    }

    public List<SongDetails> getSongDetailsFromPlayListId(Playlist playlist)
    {
        HSession hSession=new HSession(sessionFactory);
        List<SongDetails> songs = new ArrayList<>();


        try {
            hSession.openWithTransaction();
            Criteria criteria = currentSession().createCriteria(SongDetails.class);
            songs = (List<SongDetails>) criteria.add(Restrictions.eq("playlists", playlist)).list();
            hSession.commit();
        }catch (Exception e){
            logger.error("Error  Occured :: " + e.getMessage(), e);
        }finally {
            hSession.close();
        }

//        logger.info("wfmConfigList size :: "+ wfmConfigList.size());

        return songs;
    }




    @Override
    public SongDetails persist(SongDetails entity) throws HibernateException {
        return super.persist(entity);
    }
}



