package com.klm.devcase.destinationfinder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Matteo on 28/03/2015.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
@EnableJpaRepositories(basePackages = "com.klm.devcase.destinationfinder")
@ComponentScan("com.klm.devcase.destinationfinder")
@ImportResource("classpath:spring.xml")
@Slf4j
public class DestinationFinder {

    private static final Properties PROPS = new Properties();

    public static void main(String[] args) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        try (InputStream is = cl.getResourceAsStream("META-INF/maven/com/klm/devcase/destination-finder/pom.properties")) {
            PROPS.load(is);
        } catch (Exception e) {
            try (InputStream is2 = cl.getResourceAsStream("application.properties")) {
                PROPS.load(is2);
            } catch (Exception e1) {
                PROPS.put("version", "ND");
            }
        }

        log.info("Destination Finder {} loading...", PROPS.getProperty("version"));
        ApplicationContext ctx = SpringApplication.run(DestinationFinder.class, args);
    }
}
