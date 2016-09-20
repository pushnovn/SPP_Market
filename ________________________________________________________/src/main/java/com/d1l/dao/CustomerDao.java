package com.d1l.dao;

import com.d1l.model.Customer;
import com.d1l.model.User;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CustomerDao {

    public static void addOrUpdateCustomer(Customer customer) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteCustomer(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);

            if (customer != null) {
                session.delete(customer);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static Customer getCustomerById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Customer customer = null;
        try {
            Criteria criteria = session.createCriteria(Customer.class);
            criteria.add(Restrictions.eq("id", id));
            customer = (Customer)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return customer;
    }

    public static Customer getCustomerByUser(User user) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Customer customer = null;
        try {
            Criteria criteria = session.createCriteria(Customer.class);
            criteria.add(Restrictions.eq("user", user));
            customer = (Customer)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return customer;
    }

    public static List<Customer> getCustomersList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Customer> customersList = null;
        try {
            customersList = (List<Customer>)session.createCriteria(Customer.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return customersList;
    }
}
