package com.company.app.model.entity;

import lombok.Data;

@Data
public class RecipeInfo extends PersistableEntityImpl {
    private Drug drug;
    private Long recipeId;
    private boolean deleted;
}
