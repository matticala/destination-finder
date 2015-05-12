package com.klm.devcase.destinationfinder.repository;

import com.klm.devcase.destinationfinder.model.SearchResult;
import com.klm.devcase.destinationfinder.respository.DestinationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.NotFoundException;
import java.math.BigDecimal;

/**
 * Created by Matteo on 28/03/2015.
 */

public class DestinationRepositoryRestProxyIT extends AbstractSpringTestSupport {

    @Autowired
    DestinationRepository repo;

    @Test
    public void findByOriginAndPosition() {
        String origin = "AMS";
        String position = "NL";
        SearchResult result = repo.findByOriginAndPosition(origin, position);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getDestinations());
        Assert.assertTrue(result.getDestinations().length > 0);
    }

    @Test
    public void findByOriginPositionAndMaxBudget() {
        String origin = "AMS";
        String position = "NL";
        SearchResult result = repo.findByOriginPositionAndMaxBudget(origin, position, new BigDecimal(500.0));
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getDestinations());
        Assert.assertTrue(result.getDestinations().length > 0);
    }

    @Test
    public void findByOriginPositionMinBudgetAndMaxBudget() {
        String origin = "AMS";
        String position = "NL";
        SearchResult result = repo.findByOriginPositionMinBudgetAndMaxBudget(origin, position, new BigDecimal(50.0), new BigDecimal(150.0));
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getDestinations());
        Assert.assertTrue(result.getDestinations().length > 0);
    }

    @Test(expected = NotFoundException.class)
    public void findByOriginPositionAndMaxBudget2() {
        String origin = "AMS";
        String position = "NL";
        SearchResult result = repo.findByOriginPositionAndMaxBudget(origin, position, new BigDecimal(1.0));
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getDestinations());
        Assert.assertTrue(result.getDestinations().length > 0);
    }

    /*
     * On production, I should implement a positive/negative test for every combination
     */

}
