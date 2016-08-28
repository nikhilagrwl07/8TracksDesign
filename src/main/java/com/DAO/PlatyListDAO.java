package com.DAO;

import com.Models.Playlist;
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
public class PlatyListDAO extends AbstractDAO<Playlist> {

    private final static Logger logger = LoggerFactory.getLogger(PlatyListDAO.class);
    private SessionFactory sessionFactory;

    @Inject
    public PlatyListDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory=sessionFactory;

    }

    public Playlist getplayListFromplaylistName(String playlistName)
    {
        HSession hSession=new HSession(sessionFactory);
        Playlist playlist=new Playlist();

        try {
            hSession.openWithTransaction();
            Criteria criteria = currentSession().createCriteria(Playlist.class);
            playlist = (Playlist) criteria.add(Restrictions.eq("playlistName", playlistName)).uniqueResult();
            hSession.commit();
        }catch (Exception e){
            logger.error("Error  Occured :: " + e.getMessage(), e);
        }finally {
            hSession.close();
        }

//        logger.info("wfmConfigList size :: "+ wfmConfigList.size());

        return playlist;
    }





    @Override
    public Playlist persist(Playlist entity) throws HibernateException {
        return super.persist(entity);
    }
}




