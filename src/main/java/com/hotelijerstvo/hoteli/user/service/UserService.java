package com.hotelijerstvo.hoteli.user.service;

import com.hotelijerstvo.hoteli.constants.Constants;
import com.hotelijerstvo.hoteli.user.User;
import jakarta.persistence.*;

import java.util.List;

class UserService implements UserServiceLocal{


    @Override
    public List<User> findAll(){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("User.findAll");
        entityManager.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public User login(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            return null;
        }
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNamedQuery("User.findByUsernamePass");
        query.setParameter("username", username);
        query.setParameter("password", password);
        try{
            User user = (User) query.getSingleResult();
            return user;
        }catch (NoResultException|NonUniqueResultException exception){
            System.err.println(exception.getMessage());
            return null;
        }
    }

    public EntityManager getEntityManager(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.PU_NAME);
        return entityManagerFactory.createEntityManager();
    }
}
