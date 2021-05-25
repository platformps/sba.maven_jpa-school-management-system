package com.github.perscholas.dao;

import com.github.perscholas.model.EntityInterface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface JpaRepositoryInterface<IdType extends Serializable,
        EntityType extends EntityInterface<IdType>>
extends RepositoryInterface<IdType, EntityType>{

    default EntityType create(EntityType entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
        return entity;
    }

    default List<EntityType> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EntityType> cq = cb.createQuery(getEntityClass());
        Root<EntityType> rootEntry = cq.from(getEntityClass());
        CriteriaQuery<EntityType> all = cq.select(rootEntry);
        TypedQuery<EntityType> allQuery = getEntityManager().createQuery(all);
        return allQuery.getResultList();
    }

    default EntityType delete(EntityType entityToBeDeleted) {
        getEntityManager().remove(entityToBeDeleted);
        return entityToBeDeleted;
    }

    default Optional<EntityType> findById(IdType id) {
        return Optional.of(getEntityManager().find(getEntityClass(), id));
    }

    default void close() {
        getEntityManager().close();
        getEntityManagerFactory().close();
    }

    default EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(getPersistenceUnitName());
    }

    default List<EntityType> executeQuery(String sql) {
        return getEntityManager().createQuery(sql).getResultList();
    }

    Class<EntityType> getEntityClass();

    String getPersistenceUnitName();

    EntityManager getEntityManager();

}

