package com.d1l.dao;

import com.d1l.model.Item;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ItemDao {

    public static void addOrUpdateItem(Item item) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteItem(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Item item = session.get(Item.class, id);

            if (item != null) {
                session.delete(item);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static Item getItemById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Item item = null;
        try {
            Criteria criteria = session.createCriteria(Item.class);
            criteria.add(Restrictions.eq("id", id));
            item = (Item)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return item;
    }

    public static List<Item> getItemsList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Item> itemsList = null;
        try {
            itemsList = (List<Item>)session.createCriteria(Item.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return itemsList;
    }


}
