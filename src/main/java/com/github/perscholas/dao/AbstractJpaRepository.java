package com.github.perscholas.dao;

import com.github.perscholas.model.EntityInterface;

import javax.persistence.EntityManager;
import java.io.Serializable;

abstract public class AbstractJpaRepository<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>>
        implements JpaRepositoryInterface<IdType, EntityType> {
    private String persistenceUnitName;
    private EntityManager entityManager;

    public AbstractJpaRepository(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    @Override
    public EntityManager getEntityManager() {
        if(this.entityManager == null) {
            this.entityManager = getEntityManagerFactory().createEntityManager();
        }
        return this.entityManager;
    }

    @Override
    public String getPersistenceUnitName() {
        return persistenceUnitName;
    }
}