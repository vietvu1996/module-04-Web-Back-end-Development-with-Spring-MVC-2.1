package com.codegym.repository.impl;

import com.codegym.model.Picture;
import com.codegym.repository.IPictureRepository;
import com.codegym.service.IPictureService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class PictureRepository implements IPictureRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Picture> findAll() {
        TypedQuery<Picture> query = entityManager.createQuery("select c from Picture c", Picture.class);
        return query.getResultList();
    }

    @Override
    public void save(Picture picture) {
        if (picture.getId() != 0) {
            entityManager.merge(picture);
        } else {
            entityManager.persist(picture);
        }
    }

    @Override
    public Picture findById(int id) {
        TypedQuery<Picture> query = entityManager.createQuery("select c from Picture c where c.id = :id", Picture.class);
        query.setParameter("id", id);
        Picture picture;
        try {
            picture = query.getSingleResult();
        } catch (NoResultException e) {
            picture = null;
        }
        return picture;
    }

    @Override
    public Picture like(int id) {
        Picture picture = findById(id);
        picture.setLikes(picture.getLikes() + 1);
        entityManager.merge(picture);
        return picture;
    }

    @Override
    public void remove(int id) {
        Picture picture = findById(id);
        if (picture != null) entityManager.remove(picture);
    }
}
