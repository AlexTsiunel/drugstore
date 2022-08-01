package com.company.app.model.entity;

import com.company.app.model.api.PersistableEntity;
import lombok.Data;

@Data
public class PersistableEntityImpl implements PersistableEntity {
    private Long id;
}
