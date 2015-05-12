//package com.klm.devcase.destinationfinder.configuration;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Redis session management and caching can beat any other system alike,
 * but I need a Redis installation. There is no way to embed Redis in Java.
 *
 * An interesting solution could be Linkedin's Voldemort, but a Spring bridge should be written.
 *
 * Out of this devcase scope.
 *
 * Created by Matteo on 28/03/2015.
 */
//@Configuration
//@EnableRedisHttpSession
//public class SessionConfiguration {

//    @Bean
//    public JedisConnectionFactory connectionFactory() {
//        return new JedisConnectionFactory();
//    }
//}
