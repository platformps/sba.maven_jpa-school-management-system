package com.github.perscholas.model;

import java.io.Serializable;

public interface EntityInterface<IdType extends Serializable> {
    IdType getId();
}
