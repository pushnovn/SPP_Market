package com.d1l.dao;

import com.d1l.model.Customer;
import com.d1l.model.Order;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

public class OrderDao {

    public static void addOrUpdateOrder(Order order) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteOrder(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Order order = session.get(Order.class, id);

            if (order != null) {
                session.delete(order);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static Order getOrderById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Order order = null;
        try {
            Criteria criteria = session.createCriteria(Order.class);
            criteria.add(Restrictions.eq("id", id));
            order = (Order)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return order;
    }

    public static List<Order> getOrdersListByCustomer(Customer customer) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Order> orders = null;
        try {
            Criteria criteria = session.createCriteria(Order.class);
            criteria.add(Restrictions.eq("customer", customer));
            orders = (List<Order>)criteria.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return orders;
    }

    public static Order getOrderByCustomerAndLastDate(Customer customer) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Order> orders = null;
        try {
            Criteria criteria = session.createCriteria(Order.class)
                    .addOrder(org.hibernate.criterion.Order.desc("date") );
            criteria.add(Restrictions.eq("customer", customer));
            criteria.setFirstResult(0);
            criteria.setMaxResults(1);
            orders = (List<Order>)criteria.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return orders.get(0);
    }

    public static List<Order> getOrdersList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Order> ordersList = null;
        try {
            ordersList = (List<Order>)session.createCriteria(Order.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return ordersList;
    }


}
