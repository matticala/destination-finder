package com.klm.devcase.destinationfinder.respository;

import com.klm.devcase.destinationfinder.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Matteo on 28/03/2015.
 */
@Repository
public class AirportRepository {

    @Autowired
    private Client client;

    @Value("${service.klm.endpoint}")
    private String url;

    public Airport findOne(String code) {
        Invocation.Builder http = null;
        WebTarget target = client.target(url);
        target = target.path("/destination-finder/airports/" + code);
        http = target.request(MediaType.APPLICATION_JSON_TYPE);
        return http.get(Airport.class);
    }

    /*
     * This is heavy. Better to cache it
     */
    @Cacheable("airports")
    public List<Airport> findAll() {
        Invocation.Builder http = null;
        WebTarget target = client.target(url);
        target = target.path("/destination-finder/airports");
        http = target.request(MediaType.APPLICATION_JSON_TYPE);
        List<Airport> ret = Arrays.asList(http.get(Airport[].class));
        Collections.sort(ret);
        return ret;
    }

    public List<Airport> findAll(Iterable<String> codes) {
        Set<String> codeSet = new TreeSet<>();
        for(String code : codes) {
            codeSet.add(code);
        }
        Invocation.Builder http = null;
        WebTarget target = client.target(url);
        target = target.path("/destination-finder/airports");
        http = target.request(MediaType.APPLICATION_JSON_TYPE);
        Airport[] airports = http.get(Airport[].class);
        return Arrays.asList(airports).stream().filter(a -> codeSet.contains(a.getCode())).collect(Collectors.toList());
    }

}
