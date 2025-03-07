package com.jsf.dao;


import java.util.List;

import com.jsf.entities.Role;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Stateless
public class RoleDAO {

    @PersistenceContext
    private EntityManager em;

    // Pobierz listę wszystkich ról
    public List<Role> getAllRoles() {
        return em.createQuery("SELECT m FROM Role m", Role.class).getResultList();
    }

}
