package com.klm.devcase.destinationfinder.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Matteo on 28/03/2015.
 */
@Data
public class Destination implements Serializable {

    private String origin;
    private Airport destination;
    private Fare lowestFare;

}
