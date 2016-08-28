package com.DAO;

import com.Models.PlayListIdToTagId;
import com.Models.Tag;
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
public class PlayListIdToTagIdDAO extends AbstractDAO<PlayListIdToTagId> {

    private final static Logger logger = LoggerFactory.getLogger(PlayListIdToTagIdDAO.class);
    private SessionFactory sessionFactory;

    @Inject
    public PlayListIdToTagIdDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory=sessionFactory;

    }


    public List<PlayListIdToTagId> getTagDetails(Tag tag)
    {
        HSession hSession=new HSession(sessionFactory);
//        PlayListIdToTagId playListIdToTagId = new PlayListIdToTagId();
        List<PlayListIdToTagId> playListIdToTagIds=new ArrayList<>();
        try {
            hSession.openWithTransaction();
            Criteria criteria = currentSession().createCriteria(PlayListIdToTagId.class);
            playListIdToTagIds = (List<PlayListIdToTagId>) criteria.add(Restrictions.eq("tagId", tag)).list();
            hSession.commit();
        }catch (Exception e){
            logger.error("Error  Occured :: " + e.getMessage(), e);
        }finally {
            hSession.close();
        }

//        logger.info("wfmConfigList size :: "+ wfmConfigList.size());

        return playListIdToTagIds;
    }


    @Override
    public PlayListIdToTagId persist(PlayListIdToTagId entity) throws HibernateException {
        return super.persist(entity);
    }
}





