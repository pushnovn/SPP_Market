package com.d1l.dao;

import com.d1l.model.Market;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class MarketDao {

    public static void addOrUpdateMarket(Market market) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(market);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteMarket(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Market market = session.get(Market.class, id);

            if (market != null) {
                session.delete(market);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static Market getMarketById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Market market = null;
        try {
            Criteria criteria = session.createCriteria(Market.class);
            criteria.add(Restrictions.eq("id", id));
            market = (Market)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return market;
    }

    public static List<Market> getMarketsList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Market> marketsList = null;
        try {
            marketsList = (List<Market>)session.createCriteria(Market.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return marketsList;
    }
    
}
