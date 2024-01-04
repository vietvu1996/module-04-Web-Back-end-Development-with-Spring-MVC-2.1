package com.codegym.service;

import com.codegym.model.Music;
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
public class HibernateMusicService implements IMusicService {
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
    public List<Music> findAll() {
        String strQuery = "SELECT c FROM Music AS c";
        TypedQuery<Music> query = entityManager.createQuery(strQuery, Music.class);
        return query.getResultList();
    }

    @Override
    public void save(Music music) {
        Transaction transaction = null;
        Session session = null;
        Music origin;
        if (music.getId() == 0) {
            origin = new Music();
        } else {
            origin = findById(music.getId());
        }
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            origin.setMusicName(music.getMusicName());
            origin.setSingerName(music.getSingerName());
            origin.setType(music.getType());
            origin.setFileMusic(music.getFileMusic());
            session.saveOrUpdate(origin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Music findById(int id) {
        String queryStr = "SELECT c FROM Music AS c WHERE c.id = :id";
        TypedQuery<Music> query = entityManager.createQuery(queryStr, Music.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remove(int id) {
        Music music = findById(id);
        if (music != null) {
            Transaction transaction = null;
            try {
                Session session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                session.remove(music);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
}
