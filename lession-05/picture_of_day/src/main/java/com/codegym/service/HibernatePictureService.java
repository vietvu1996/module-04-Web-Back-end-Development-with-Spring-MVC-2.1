package com.codegym.service;

import com.codegym.model.Picture;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class HibernatePictureService implements IPictureService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Picture> findAll() {
        String queryStr = "SELECT c FROM Picture AS c";
        TypedQuery<Picture> query = entityManager.createQuery(queryStr, Picture.class);
        return query.getResultList();
    }

    @Override
    public void save(Picture picture) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            picture.setLikes(0);
            picture.setAuthorName(picture.getAuthorName());
            picture.setComment(picture.getComment());
            session.saveOrUpdate(picture);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Picture findById(int id) {
        String query = "SELECT c FROM Picture AS c WHERE c.id = :id";
        TypedQuery<Picture> typedQuery = entityManager.createQuery(query, Picture.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    @Override
    public Picture like(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Picture picture = findById(id);
            if (picture != null) {
                picture.setLikes(picture.getLikes() + 1);
                session.saveOrUpdate(picture);
                transaction.commit();
                return picture;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Picture update(Picture picture) {
        Transaction transaction = null;
        Picture origin;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin = findById(picture.getId());
            if (origin != null) {
                origin.setComment(picture.getComment());
                origin.setAuthorName(picture.getAuthorName());
                origin.setRate(picture.getRate());
                session.saveOrUpdate(origin);
                transaction.commit();
                return origin;
            }
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return null;
    }
}
