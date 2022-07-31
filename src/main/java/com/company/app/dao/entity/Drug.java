package com.company.app.dao.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Drug extends PersistableEntityImpl {
    private String name;
    private String releaseForm;
    private DosageForm dosageForm;
    private RouteAdministration routeAdministration;
    private Boolean isRecipe;
    private BigDecimal price;
    private boolean deleted;

    public enum DosageForm{
        TABLETS,
        CAPSULES,
        SUSPENSION,
        INGECTION
    }
    public enum RouteAdministration{
        ORALLY,
        PARENTERALLY
        //
    }
}
