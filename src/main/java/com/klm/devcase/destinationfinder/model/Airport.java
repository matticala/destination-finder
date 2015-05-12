package com.klm.devcase.destinationfinder.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Matteo on 28/03/2015.
 */
@Data
@Entity
public class Airport implements Serializable, Cloneable, Comparable<Airport> {

    @Id
    @NotNull
    @Size(max = 3, min = 3)
    private String code;
    @NotNull
    private String type;
    private Airport parent;
    @NotNull
    private String name;
    private String description;
    private Coordinates coordinates;
    private String country;
    private String continent;

    @Override
    public int compareTo(Airport o) {
        return this.code.compareTo(o.code);
    }
}
