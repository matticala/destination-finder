package com.klm.devcase.destinationfinder.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Matteo on 28/03/2015.
 */
@Data
public class RequestDataInfo implements Serializable {

    private Long numberOfRequests;
    private Long averageExecutionTime;

}
