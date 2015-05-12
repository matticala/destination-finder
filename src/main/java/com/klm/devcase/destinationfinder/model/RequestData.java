package com.klm.devcase.destinationfinder.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;

/**
 * Created by Matteo on 28/03/2015.
 */
@Data
@Entity
public class RequestData implements Serializable, Comparable<RequestData>, Cloneable {

    @Id
    @GeneratedValue
    private Long id;
    private Instant begin;
    private Instant end;
    private Long duration;

    @Override
    public int compareTo(RequestData o) {
        return Long.compare(this.duration, o.duration);
    }

    public RequestData copy() {
        try {
            return (RequestData) this.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
