package com.company.app.model.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Convert<D extends Dto, E extends PersistableEntity> {
    public D convertEntityToDto(E entity) {
        return null;
    }

    public E convertDtoToEntity(D dto) {
        return null;
    }

     public List<E> convertDtosToEntities(List<D> dtos) {
        if (dtos != null) {
            return dtos.stream().map(this::convertDtoToEntity).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<D> convertEntitiesToDtos(List<E> entities) {
        if (entities != null) {
            return entities.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}
