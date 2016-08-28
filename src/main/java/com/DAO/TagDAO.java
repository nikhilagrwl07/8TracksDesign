package com.DAO;

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
public class TagDAO extends AbstractDAO<Tag> {

    private final static Logger logger = LoggerFactory.getLogger(TagDAO.class);
    private SessionFactory sessionFactory;

    @Inject
    public TagDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory=sessionFactory;

    }


    public Tag getTagDetails(String tagName)
    {
        HSession hSession=new HSession(sessionFactory);
        Tag tag=new Tag();

        try {
            hSession.openWithTransaction();
            Criteria criteria = currentSession().createCriteria(Tag.class);
            tag = (Tag) criteria.add(Restrictions.eq("tagData", tagName)).uniqueResult();
            hSession.commit();
        }catch (Exception e){
            logger.error("Error  Occured :: " + e.getMessage(), e);
        }finally {
            hSession.close();
        }

//        logger.info("wfmConfigList size :: "+ wfmConfigList.size());

        return tag;
    }




    @Override
    public Tag persist(Tag entity) throws HibernateException {
        return super.persist(entity);
    }
}





