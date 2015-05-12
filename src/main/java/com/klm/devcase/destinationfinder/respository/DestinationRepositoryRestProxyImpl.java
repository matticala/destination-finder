package com.klm.devcase.destinationfinder.respository;

import com.klm.devcase.destinationfinder.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.net.URI;

/**
 * Created by Matteo on 28/03/2015.
 */
@Repository
public class DestinationRepositoryRestProxyImpl implements DestinationRepository {

    @Autowired
    Client rest;

    @Value("${service.klm.endpoint}")
    private URI base;

    @Value("${service.klm.df.endpoint}")
    private URI path;

    @Override
    public SearchResult findByOriginAndPosition(String origin, String position) {
        return findByOriginPositionMinBudgetAndMaxBudget(origin, position, null, null);
    }

    @Override
    public SearchResult findByOriginPositionAndMaxBudget(String origin, String position, BigDecimal maxBudget) {
        return findByOriginPositionMinBudgetAndMaxBudget(origin, position, null, maxBudget);
    }

    @Override
    public SearchResult findByOriginPositionMinBudgetAndMaxBudget(String origin, String position, BigDecimal minBudget, BigDecimal maxBudget) {
        Invocation.Builder get = null;
        WebTarget target = rest.target(base.resolve(path));
        target = target.queryParam("origin", origin)
                .queryParam("pos", position.toUpperCase()); // if lowercase BOOM!
        if (minBudget != null) {
            target = target.queryParam("minBudget", minBudget);
        }
        if (maxBudget != null) {
            target = target.queryParam("maxBudget", maxBudget);
        }
        get = target.request(MediaType.APPLICATION_JSON_TYPE);

        return get.get(SearchResult.class);
    }
}
