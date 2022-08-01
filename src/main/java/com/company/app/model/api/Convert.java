package com.company.app.model.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface Convert<D extends Dto, E extends PersistableEntity> {
    D convertEntityToDto(E entity);

    E convertDtoToEntity(D dto);

    default List<E> convertDtosToEntities(List<D> dtos) {
        if (dtos != null) {
            return dtos.stream().map(this::convertDtoToEntity).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    default List<D> convertEntitiesToDtos(List<E> entities) {
        if (entities != null) {
            return entities.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}
