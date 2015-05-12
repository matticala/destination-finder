package com.klm.devcase.destinationfinder.comparator;

import com.klm.devcase.destinationfinder.model.Destination;

import java.util.Comparator;

/**
 * Sorts by airport name, descending
 *
 * Created by Matteo on 28/03/2015.
 */
public class CompareDestinationByAirport implements Comparator<Destination> {

    @Override
    public int compare(Destination o1, Destination o2) {
        return o1.getDestination().compareTo(o2.getDestination());
    }
}
