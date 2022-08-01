package com.company.app.model.converter;

import com.company.app.model.api.Convert;
import com.company.app.model.dto.DrugDto;
import com.company.app.model.entity.Drug;

public class DrugConverter implements Convert<DrugDto, Drug> {
    @Override
    public DrugDto convertEntityToDto(Drug entity) {
        DrugDto drugDto = new DrugDto();
        if(entity != null){
            drugDto.setId(entity.getId());
            drugDto.setName(entity.getName());
            drugDto.setReleaseForm(entity.getReleaseForm());
            drugDto.setDosageForm(toDosageFormDto(entity.getDosageForm()));
            drugDto.setRouteAdministration(toRouteAdministrationDto(entity.getRouteAdministration()));
            drugDto.setIsRecipe(entity.getIsRecipe());
            drugDto.setPrice(entity.getPrice());
            drugDto.setQuantityInStock(entity.getQuantityInStock());
        }
        return drugDto;
    }
    @Override
    public Drug convertDtoToEntity(DrugDto dto) {
        Drug drug = new Drug();
        if(dto != null){
            drug.setId(dto.getId());
            drug.setName(dto.getName());
            drug.setReleaseForm(dto.getReleaseForm());
            drug.setDosageForm(toDosageFormEntity(dto.getDosageForm()));
            drug.setRouteAdministration(toRouteAdministrationEntity(dto.getRouteAdministration()));
            drug.setIsRecipe(dto.getIsRecipe());
            drug.setPrice(dto.getPrice());
            drug.setQuantityInStock(dto.getQuantityInStock());
        }
        return drug;
    }
    private Drug.DosageForm toDosageFormEntity(DrugDto.DosageForm dtoDosageForm){
        int ordinal = dtoDosageForm.ordinal();
        Drug.DosageForm[] entityDosageForms = Drug.DosageForm.values();
        return entityDosageForms[ordinal];
    }

    private DrugDto.DosageForm toDosageFormDto(Drug.DosageForm entityDosageForm){
        int ordinal = entityDosageForm.ordinal();
        DrugDto.DosageForm[] dtoDosageForms = DrugDto.DosageForm.values();
        return dtoDosageForms[ordinal];
    }

    private Drug.RouteAdministration toRouteAdministrationEntity(DrugDto.RouteAdministration dtoRouteAdministration){
        int ordinal = dtoRouteAdministration.ordinal();
        Drug.RouteAdministration[] entityRouteAdministrations= Drug.RouteAdministration.values();
        return entityRouteAdministrations[ordinal];
    }

    private DrugDto.RouteAdministration toRouteAdministrationDto(Drug.RouteAdministration entityRouteAdministration){
        int ordinal = entityRouteAdministration.ordinal();
        DrugDto.RouteAdministration[] dtoRouteAdministrations = DrugDto.RouteAdministration.values();
        return dtoRouteAdministrations[ordinal];
    }
}
