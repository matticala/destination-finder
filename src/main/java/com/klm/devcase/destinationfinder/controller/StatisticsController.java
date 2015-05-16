package com.klm.devcase.destinationfinder.controller;

import com.klm.devcase.destinationfinder.model.Statistic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is dirty, really, but works.
 *
 * A proper service-repository stack is better approach. Also a better mapping, maybe.
 *
 * Created by Matteo on 28/03/2015.
 */
@RestController
public class StatisticsController {

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Statistic getStatistic() {
        Query q = entityManager.createQuery("SELECT count(s) as requestNumber, avg(s.millis) as averageServingTime FROM Served s");
        Object[] values = (Object[]) q.getSingleResult();
        Statistic s = new Statistic();
        s.setRequestNumber((Long) values[0]);
        s.setAverageServingTime((Double) values[1]);
        return s;
    }
}
