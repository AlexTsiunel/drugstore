package com.company.app.service.impl;

import com.company.app.dao.RecipeDao;
import com.company.app.model.converter.RecipeConverter;
import com.company.app.model.dto.RecipeDto;
import com.company.app.model.entity.Recipe;
import com.company.app.service.RecipeService;

import java.util.List;

public class RecipeServiceImpl implements RecipeService {
    private RecipeDao recipeDao;
    private RecipeConverter recipeConverter;

    public RecipeServiceImpl(RecipeDao recipeDao, RecipeConverter recipeConverter) {
        this.recipeDao = recipeDao;
        this.recipeConverter = recipeConverter;
    }

    @Override
    public RecipeDto getById(Long id) {
        return recipeConverter.convertEntityToDto(recipeDao.getById(id));
    }

    @Override
    public RecipeDto saveOrUpdate(RecipeDto recipeDto) {
        Recipe recipe = recipeDao.saveOrUpdate(recipeConverter.convertDtoToEntity(recipeDto));
        return recipeConverter.convertEntityToDto(recipe);
    }

    @Override
    public List<RecipeDto> getAll() {
        return recipeConverter.convertEntitiesToDtos(recipeDao.getAll());
    }

    @Override
    public void delete(Long id) {
        recipeDao.getAll();
    }
}
