package com.company.app.model.converter;

import com.company.app.model.api.Convert;
import com.company.app.model.dto.RecipeDto;
import com.company.app.model.entity.Recipe;

public class RecipeConverter implements Convert<RecipeDto, Recipe> {
    private ClientConverter clientConverter;
    private DoctorConverter doctorConverter;
    private DrugConverter drugConverter;

    public RecipeConverter(ClientConverter clientConverter, DoctorConverter doctorConverter, DrugConverter drugConverter) {
        this.clientConverter = clientConverter;
        this.doctorConverter = doctorConverter;
        this.drugConverter = drugConverter;
    }

    @Override
    public RecipeDto convertEntityToDto(Recipe entity) {
        RecipeDto recipeDto = new RecipeDto();
        if(entity!=null){
            recipeDto.setId(entity.getId());
            recipeDto.setClient(clientConverter.convertEntityToDto(entity.getClient()));
            recipeDto.setDrugs(drugConverter.convertEntitiesToDtos(entity.getDrugs()));
            recipeDto.setStartDate(entity.getStartDate());
            recipeDto.setEndDate(entity.getEndDate());
            recipeDto.setDeleted(entity.isDeleted());
        }
        return recipeDto;
    }

    @Override
    public Recipe convertDtoToEntity(RecipeDto dto) {
        Recipe recipe = new Recipe();
        if(dto!=null){
            recipe.setId(dto.getId());
            recipe.setClient(clientConverter.convertDtoToEntity(dto.getClient()));
            recipe.setDrugs(drugConverter.convertDtosToEntities(dto.getDrugs()));
            recipe.setStartDate(dto.getStartDate());
            recipe.setEndDate(dto.getEndDate());
            recipe.setDeleted(dto.isDeleted());
        }
        return recipe;
    }
}
