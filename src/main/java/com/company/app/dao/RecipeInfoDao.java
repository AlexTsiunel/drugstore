package com.company.app.dao;

import com.company.app.model.entity.Drug;
import com.company.app.model.entity.RecipeInfo;

import java.util.List;

public interface RecipeInfoDao extends AbstractDao<Long, RecipeInfo>{
    List<Drug> getAllByRecipeId(long id);
}
