package com.github.perscholas.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public abstract class AbstractJpaRepository<E, ID> implements JpaRepository<E, ID> {
    
    private final EntityManagerFactory entityManagerFactory;
    
    public AbstractJpaRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    
    @Override
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
    
    @Override
    public List<E> findAll() {
        EntityManager entityManager = getEntityManager();
        TypedQuery typedQuery = entityManager.createQuery(
                String.format("SELECT e FROM %s e", getEntityClass().getSimpleName()),
                getEntityClass());
        List<E> entities = typedQuery.getResultList();
        entityManager.close();
        return entities;
    }
    
    @Override
    public Optional<E> findBy(String fieldName, Object value){
        EntityManager entityManager = getEntityManager();
        TypedQuery<E> typedQuery = entityManager.createQuery(String.format("SELECT e FROM %s e WHERE e.%s = :%s", getEntityClass().getSimpleName(), fieldName, fieldName, value), getEntityClass());
        typedQuery.setParameter(String.format("%s", fieldName), value);
        Optional<E> opCourse = Optional.empty();
        try {
            opCourse = Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException exception) {
        
        }
        entityManager.close();
        return opCourse;
    }
}
