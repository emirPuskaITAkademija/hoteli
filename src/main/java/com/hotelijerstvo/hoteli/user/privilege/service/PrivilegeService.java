package com.hotelijerstvo.hoteli.user.privilege.service;

import com.hotelijerstvo.hoteli.constants.Constants;
import com.hotelijerstvo.hoteli.user.privilege.Privilege;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

class PrivilegeService implements PrivilegeServiceLocal{
    @Override
    public List<Privilege> findAll() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNamedQuery("Privilege.findAll");
        return query.getResultList();
    }

    public EntityManager getEntityManager(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.PU_NAME);
        return entityManagerFactory.createEntityManager();
    }
}
