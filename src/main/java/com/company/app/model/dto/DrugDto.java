package com.company.app.model.dto;

import com.company.app.model.api.Dto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DrugDto implements Dto {
    private Long id;
    private String name;
    private String releaseForm;
    private DosageForm dosageForm;
    private RouteAdministration routeAdministration;
    private Boolean isRecipe;
    private BigDecimal price;
    private Integer quantityInStock;
    private boolean deleted;

    public enum DosageForm{
        TABLET,
        CAPSULE,
        SUSPENSION,
        INJECTION
    }
    public enum RouteAdministration{
        ORALLY,
        PARENTERAL
        //
    }
}
