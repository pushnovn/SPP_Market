package com.d1l.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private final static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            return  configuration.configure().buildSessionFactory();
        } catch (ExceptionInInitializerError e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw e;
        }
    }

    public static Session makeSession() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (org.hibernate.HibernateException he) {
            session = sessionFactory.openSession();
        }
        return session;
    }

}
