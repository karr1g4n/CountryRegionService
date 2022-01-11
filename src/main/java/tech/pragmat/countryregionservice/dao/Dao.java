package tech.pragmat.countryregionservice.dao;

import java.util.List;

public interface Dao<T, K> {

    boolean update(T entity);

    boolean deleteByName(String name);

    void create(T entity) throws Exception;

    List<T> findAll();

    T getById(K id) throws Exception;

}
