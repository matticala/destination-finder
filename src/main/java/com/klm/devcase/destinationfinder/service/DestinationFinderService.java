package com.klm.devcase.destinationfinder.service;

import com.klm.devcase.destinationfinder.model.Destination;
import com.klm.devcase.destinationfinder.respository.DestinationRepository;
import com.klm.devcase.destinationfinder.respository.ServedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matteo on 28/03/2015.
 */
@Service
public class DestinationFinderService {

    @Autowired
    DestinationRepository repository;

    @Autowired
    ServedRepository servedRepository;


    public List<Destination> findByOriginPosMinBudgetAndMaxBudget(String origin, String pos, BigDecimal minBudget, BigDecimal maxBudget) {
        ArrayList<Destination> ret = new ArrayList<>();
        ret.addAll(Arrays.asList(repository.findByOriginPositionMinBudgetAndMaxBudget(origin, pos, minBudget, maxBudget).getDestinations()));
        return ret;
    }
}
