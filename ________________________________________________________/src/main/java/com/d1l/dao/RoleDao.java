package com.d1l.dao;

import com.d1l.model.Role;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class RoleDao {

    public static void addOrUpdateRole(Role role) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(role);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteRole(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Role role = session.get(Role.class, id);

            if (role != null) {
                session.delete(role);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static List<Role> getRolesList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Role> rolesList = null;
        try {
            rolesList = (List<Role>)session.createCriteria(Role.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return rolesList;
    }

    public static Role getRoleById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Role role = null;
        try {
            Criteria criteria = session.createCriteria(Role.class);
            criteria.add(Restrictions.eq("id", id));
            role = (Role)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return role;
    }

    public static Role getRoleByName(String name) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Role role = null;
        try {
            Criteria criteria = session.createCriteria(Role.class);
            criteria.add(Restrictions.eq("name", name));
            role = (Role)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return role;
    }

}

