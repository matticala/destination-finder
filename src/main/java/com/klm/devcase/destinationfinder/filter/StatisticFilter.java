package com.klm.devcase.destinationfinder.filter;

import com.klm.devcase.destinationfinder.model.Served;
import com.klm.devcase.destinationfinder.respository.ServedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * Very rusty implementation. It catches also requests for CSS and JS, sorting...
 *
 * Created by Matteo on 28/03/2015.
 */
@Component
public class StatisticFilter implements Filter {

    @Autowired
    ServedRepository repository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        Served s = new Served();
        s.setMillis(end - start);
        repository.save(s);
    }

    @Override
    public void destroy() {

    }
}
