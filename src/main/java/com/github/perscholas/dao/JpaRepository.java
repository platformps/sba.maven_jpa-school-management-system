package com.github.perscholas.dao;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface JpaRepository<E, ID> {
    
    /**
     * @return EntityManager that is used to perform queries on the database
     */
    public EntityManager getEntityManager();
    
    /**
     * @return the specific Entity class
     */
    public Class<E> getEntityClass();
    
    /**
     * Finds all Entities for specific type
     * @return List of entites of type T
     */
    public List<E> findAll();
    
    
    public Optional<E> findBy(String fieldName, Object value);
    
}
