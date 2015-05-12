package com.klm.devcase.destinationfinder.service;

import com.klm.devcase.destinationfinder.model.Airport;
import com.klm.devcase.destinationfinder.respository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Matteo on 28/03/2015.
 */
@Service
public class AirportService {

    @Autowired
    AirportRepository repository;

    public Airport findOne(String code) {
        return repository.findOne(code);
    }

    public List<Airport> findAll() {
        return repository.findAll();
    }

    @Async
    public Future<List<Airport>> findAllAsync() {
        return new AsyncResult<>(repository.findAll());
    }

    public List<Airport> findAll(Iterable<String> codes) {
        return repository.findAll(codes);
    }

}
