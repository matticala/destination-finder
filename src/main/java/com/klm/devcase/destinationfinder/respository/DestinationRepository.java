package com.klm.devcase.destinationfinder.respository;

import com.klm.devcase.destinationfinder.model.SearchResult;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigDecimal;

import static com.klm.devcase.destinationfinder.configuration.SharedConfiguration.CACHE_SEARCHES;

/**
 * Created by Matteo on 28/03/2015.
 */
public interface DestinationRepository {

    @Cacheable(CACHE_SEARCHES)
    SearchResult findByOriginAndPosition(String origin, String position);

    @Cacheable(CACHE_SEARCHES)
    SearchResult findByOriginPositionAndMaxBudget(String origin, String position, BigDecimal maxBudget);

    @Cacheable(CACHE_SEARCHES)
    SearchResult findByOriginPositionMinBudgetAndMaxBudget(String origin, String position,
                                                           BigDecimal minBudget, BigDecimal maxBudget);
}
