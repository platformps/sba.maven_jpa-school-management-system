package com.github.perscholas.service;

import com.github.perscholas.dao.RepositoryInterface;
import com.github.perscholas.model.EntityInterface;

import java.io.Serializable;

public class AbstractService<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>,
        RepositoryType extends RepositoryInterface<IdType, EntityType>>
        implements ServiceInterface<IdType, EntityType, RepositoryType> {
    private RepositoryType repository;

    public AbstractService(RepositoryType repository) {
        this.repository = repository;
    }

    @Override
    public RepositoryType getRepository() {
        return repository;
    }
}