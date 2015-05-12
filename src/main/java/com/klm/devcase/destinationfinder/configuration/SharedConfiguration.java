package com.klm.devcase.destinationfinder.configuration;

import com.klm.devcase.destinationfinder.DestinationFinder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.logging.Logger;

/**
 * Created by Matteo on 28/03/2015.
 */
@Configuration
public class SharedConfiguration {

    public static final String CACHE_SEARCHES = "searches";
    public static final String CACHE_AIRPORTS = "airports";

    @Bean
    ClientConfig clientConfig() {

        ClientConfig config = new ClientConfig();
        config.register(JacksonFeature.class);
        LoggingFilter loggingFilter = new LoggingFilter(
                Logger.getLogger(DestinationFinder.class.getName()),
                true);
        config.register(loggingFilter);
        return config;
    }

    @Bean
    Client client(ClientConfig clientConfig) {
        return ClientBuilder.newClient(clientConfig);
    }

    @Bean
    public CacheManager cacheManager() {

        return new ConcurrentMapCacheManager(CACHE_SEARCHES, CACHE_AIRPORTS);
    }
}
