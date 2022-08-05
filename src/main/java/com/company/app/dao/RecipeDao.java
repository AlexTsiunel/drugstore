package com.company.app.dao;

import com.company.app.model.entity.Recipe;

import java.util.List;

public interface RecipeDao extends AbstractDao<Long, Recipe> {
    List<Recipe> getAllByDoctorId(long id);

    List<Recipe> getAllByClientId(long id);

}
