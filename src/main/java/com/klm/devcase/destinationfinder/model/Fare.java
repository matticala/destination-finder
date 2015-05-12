package com.klm.devcase.destinationfinder.model;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Matteo on 28/03/2015.
 */
@Data
public class Fare implements Comparable<Fare>, Serializable {

    @NotNull @Min(0) @DecimalMin("0")
    private BigDecimal value;
    private String currency;

    @Override
    public int compareTo(Fare o) {
        if(this.currency.equals(o.currency)) {
            return value.compareTo(o.value);
        } else {
            // TODO: this should have a currency converter
            return value.compareTo(o.value);
        }
    }
}
