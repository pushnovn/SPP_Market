package com.d1l.dao;

import com.d1l.model.Category;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CategoryDao {

    public static void addOrUpdateCategory(Category category) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(category);
            session.getTransaction().commit();
        } catch (Exception e)
        {
            // TODO: Переадресовать на страницу с ошибкой вместо распечатки стектрейса
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteCategory(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Category category = session.get(Category.class, id);

            if (category != null) {
                session.delete(category);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            // TODO: Переадресовать на страницу с ошибкой вместо распечатки стектрейса
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static Category getCategoryById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Category category = null;
        try {
            Criteria criteria = session.createCriteria(Category.class);
            criteria.add(Restrictions.eq("id", id));
            category = (Category)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {

            // TODO: Переадресовать на страницу с ошибкой вместо распечатки стектрейса
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return category;
    }

    public static List<Category> getCategoriesList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Category> categoriesList = null;
        try {
            categoriesList = (List<Category>)session.createCriteria(Category.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e)
        {
            // TODO: Переадресовать на страницу с ошибкой вместо распечатки стектрейса
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return categoriesList;
    }


}
