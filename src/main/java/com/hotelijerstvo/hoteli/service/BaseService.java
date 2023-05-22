package com.hotelijerstvo.hoteli.service;

import java.util.List;

/**
 * CRUD
 * @param <E>
 * @param <P>
 */
public interface BaseService<E, P> {

    List<E> findAll();

    E findBy(P primaryKey);

    void save(E entity);

    void delete(E entity);

    E update(E entity);
}
