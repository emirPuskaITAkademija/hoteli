package com.hotelijerstvo.hoteli.reservation.room.service;

import com.hotelijerstvo.hoteli.constants.Constants;
import com.hotelijerstvo.hoteli.reservation.room.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

class RoomService implements RoomServiceLocal{
    @Override
    public List<Room> findAll() {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Room.findAll");
        return query.getResultList();
    }

    @Override
    public Room findBy(Integer id) {
        EntityManager em = getEntityManager();
        return em.find(Room.class, id);
    }

    @Override
    public void save(Room room) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(room);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Room room) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        room = em.contains(room) ? room: em.merge(room);
        em.remove(room);
        em.getTransaction().commit();
    }

    @Override
    public Room update(Room room) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        room = em.merge(room);
        em.getTransaction().commit();
        return room;
    }

    public EntityManager getEntityManager(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.PU_NAME);
        return entityManagerFactory.createEntityManager();
    }
}
