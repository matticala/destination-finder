package com.klm.devcase.destinationfinder.model;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Matteo on 28/03/2015.
 */
@Data
public class Request {
    @NotNull
    private String origin;
    @NotNull
    private String pos;
    @Min(0) @DecimalMin("0")
    private BigDecimal minBudget;
    @Min(0) @DecimalMin("0")
    private BigDecimal maxBudget;
    private String sortBy;
}
