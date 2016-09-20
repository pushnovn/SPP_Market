package com.d1l.dao;

import com.d1l.model.Supplier;
import com.d1l.model.User;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SupplierDao {

    public static void addOrUpdateSupplier(Supplier supplier) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(supplier);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteSupplier(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Supplier supplier = session.get(Supplier.class, id);

            if (supplier != null) {
                session.delete(supplier);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static Supplier getSupplierById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Supplier supplier = null;
        try {
            Criteria criteria = session.createCriteria(Supplier.class);
            criteria.add(Restrictions.eq("id", id));
            supplier = (Supplier)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return supplier;
    }

    public static Supplier getSupplierByUser(User user) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Supplier supplier = null;
        try {
            Criteria criteria = session.createCriteria(Supplier.class);
            criteria.add(Restrictions.eq("user", user));
            supplier = (Supplier)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return supplier;
    }

    public static List<Supplier> getSuppliersList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Supplier> suppliersList = null;
        try {
            suppliersList = (List<Supplier>)session.createCriteria(Supplier.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return suppliersList;
    }
}
