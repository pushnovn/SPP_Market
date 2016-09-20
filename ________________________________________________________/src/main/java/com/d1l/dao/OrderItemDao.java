package com.d1l.dao;

import com.d1l.model.OrderItem;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class OrderItemDao {

    public static void addOrUpdateOrderItem(OrderItem orderItem) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(orderItem);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteOrderItem(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            OrderItem orderItem = session.get(OrderItem.class, id);

            if (orderItem != null) {
                session.delete(orderItem);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static List<OrderItem> getOrderItemsByOrderId(int orderId) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<OrderItem> orderItemsList = null;
        try {
            Criteria criteria = session.createCriteria(OrderItem.class);
            criteria.add(Restrictions.eq("orderId", orderId));
            orderItemsList = (List<OrderItem>)criteria.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return orderItemsList;
    }

    public static List<OrderItem> getOrderItemsList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<OrderItem> orderItemsList = null;
        try {
            orderItemsList = (List<OrderItem>)session.createCriteria(OrderItem.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return orderItemsList;
    }


}
