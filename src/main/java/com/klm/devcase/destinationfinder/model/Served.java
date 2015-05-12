package com.klm.devcase.destinationfinder.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * I couldn't find a better name!
 *
 * Created by Matteo on 28/03/2015.
 */
@Data
@Entity
public class Served {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long millis;

}
