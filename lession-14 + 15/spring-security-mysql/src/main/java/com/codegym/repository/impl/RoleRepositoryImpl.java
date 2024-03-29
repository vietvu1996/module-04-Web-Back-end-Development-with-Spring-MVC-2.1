package com.codegym.repository.impl;

import com.codegym.model.Role;
import com.codegym.repository.RoleRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
public class RoleRepositoryImpl implements RoleRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Role> findAll() {
        TypedQuery<Role> query = em.createQuery("select r from Role r", Role.class);
        return query.getResultList();
    }

    @Override
    public Role findById(int id) {
        TypedQuery<Role> query = em.createQuery("select r from Role r where r.id =: id", Role.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Role model) {
        if (model.getId() == 0) {
            em.merge(model);
        } else {
            em.persist(model);
        }
    }

    @Override
    public void remove(int id) {
        Role role = findById(id);
        if (role != null) {
            em.remove(role);
        }
    }
}
