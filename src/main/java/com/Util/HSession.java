package com.Util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HSession {
    private SessionFactory sessionFactory;
    private Session currentSession;
    private Session newSession;

    private static final Logger logger = LoggerFactory.getLogger(HSession.class);

    public HSession(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getNewSession() {
        return newSession;
    }

    public void open() {
        currentSession = getCurrentSession();

        if (currentSession == null) {
            newSession = sessionFactory.openSession();
            ManagedSessionContext.bind(newSession);
        }
    }

    public void openWithTransaction() {
        currentSession = getCurrentSession();

        if (currentSession == null) {
            newSession = sessionFactory.openSession();
            ManagedSessionContext.bind(newSession);
            newSession.beginTransaction();
        }
    }

    public void openSeparateTransaction() {
        currentSession = getCurrentSession();

        newSession = sessionFactory.openSession();
        if (newSession == null) {
// logger.error("FAILED TO CREATE NEW SESSION.");
            return;
        } else {
// logger.info("FOUND NEW SESSION");
        }
        ManagedSessionContext.bind(newSession);
        newSession.beginTransaction();
    }

    public void close() {
        if (newSession != null) {
            newSession.close();
            ManagedSessionContext.unbind(sessionFactory);
        }

        if (currentSession != null) {
            ManagedSessionContext.bind(currentSession);
        }
    }

    public Session getCurrentSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
// logger.info("no current session found");
        }
        return null;
    }

    public void commit() {
        if (newSession != null) {
            newSession.flush();
            Transaction transaction = newSession.getTransaction();
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
    }

}