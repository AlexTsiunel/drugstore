package com.company.app.model.converter;

import com.company.app.model.api.Convert;
import com.company.app.model.dto.RecipeDto;
import com.company.app.model.entity.Recipe;

public class RecipeConverter extends Convert<RecipeDto, Recipe> {

    private ClientConverter clientConverter;
    private DoctorConverter doctorConverter;

    public RecipeConverter(ClientConverter clientConverter, DoctorConverter doctorConverter) {
        this.clientConverter = clientConverter;
        this.doctorConverter = doctorConverter;
    }

    @Override
    public RecipeDto convertEntityToDto(Recipe entity) {
        RecipeDto recipeDto = new RecipeDto();
        if(entity!=null){
            recipeDto.setId(entity.getId());
            recipeDto.setClient(clientConverter.convertEntityToDto(entity.getClient()));
            recipeDto.setDoctor(doctorConverter.convertEntityToDto(entity.getDoctor()));
            recipeDto.setStartDate(entity.getStartDate());
            recipeDto.setEndDate(entity.getEndDate());
            recipeDto.setDeleted(entity.isDeleted());
        }
        return recipeDto;
    }

    @Override
    public Recipe convertDtoToEntity(RecipeDto recipeDto) {
        Recipe recipe = new Recipe();
        if(recipeDto!=null){
            recipe.setId(recipeDto.getId());
            recipe.setClient(clientConverter.convertDtoToEntity(recipeDto.getClient()));
            recipe.setDoctor(doctorConverter.convertDtoToEntity(recipeDto.getDoctor()));
            recipe.setStartDate(recipeDto.getStartDate());
            recipe.setEndDate(recipeDto.getEndDate());
            recipe.setDeleted(recipeDto.isDeleted());
        }
        return recipe;
    }
}
