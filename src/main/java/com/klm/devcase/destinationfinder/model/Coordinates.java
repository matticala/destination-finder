package com.klm.devcase.destinationfinder.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Matteo on 28/03/2015.
 */
@Data
public class Coordinates implements Serializable {

    @NotNull
    private BigDecimal lat;
    @NotNull
    private BigDecimal lon;

}
