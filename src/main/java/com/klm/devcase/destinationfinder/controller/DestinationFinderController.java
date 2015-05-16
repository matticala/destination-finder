package com.klm.devcase.destinationfinder.controller;

import com.klm.devcase.destinationfinder.comparator.CompareDestinationByAirport;
import com.klm.devcase.destinationfinder.comparator.CompareDestinationByFare;
import com.klm.devcase.destinationfinder.model.Airport;
import com.klm.devcase.destinationfinder.model.Destination;
import com.klm.devcase.destinationfinder.model.Request;
import com.klm.devcase.destinationfinder.service.AirportService;
import com.klm.devcase.destinationfinder.service.DestinationFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Having a Redis instance, session could be awesomely managed by Spring Session!
 * <p>
 * Created by Matteo on 28/03/2015.
 */
@Controller("/")
@SessionAttributes({"result", "page"})
public class DestinationFinderController {

    @Autowired
    private DestinationFinderService service;

    @Autowired
    private AirportService airportService;

    @RequestMapping(value = {"/", "${service.df.endpoint}"}, produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET)
    public String index() {
        return "finder";
    }

    /*
     * Not wired to the view. It's missing pagination which can be done in 4 different ways: (bottom up)
     * - Hibernate pagination (DB needed)
     * - Service pagination through cache (fast)
     * - Session pagination (best with Spring Session and Redis)
     * - Client-side JavaScript pagination (excluded by devcase requirements)
     */
//    This should be used with WebFlow, my original intent
//    @RequestMapping(value = "${service.df.endpoint}/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Destination> search(final Request req) {
//        return service.findByOriginPosMinBudgetAndMaxBudget(req.getOrigin(), req.getPos(), req.getMinBudget(), req.getMaxBudget());
//    }

    @RequestMapping(value = {"/search", "${service.df.endpoint}/search/"}, method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String search(final Request req, final ModelMap modelMap, final HttpSession session) {
        List<Destination> result = service.findByOriginPosMinBudgetAndMaxBudget(req.getOrigin(), req.getPos(), req.getMinBudget(), req.getMaxBudget());
        PagedListHolder pages = new PagedListHolder(result);
        pages.setPageSize(10);
        session.setAttribute("DestinationFinderController_result", pages);
        pages.setPage(1);
        modelMap.put("pages", pages);
        return "finder";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = {"/search/{page}", "${service.df.endpoint}/search/{page}"}, method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String search(@PathVariable Integer page, final ModelMap modelMap, final HttpSession session) {
        PagedListHolder<Destination> pages = (PagedListHolder<Destination>) session.getAttribute("DestinationFinderController_result");
        pages.setPage(page);
        modelMap.put("pages", pages);
        return "finder";
    }

    /*
     * On a better implementation, I should not expose a new endpoint to sort results. It should be a query param on the search
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = {"/sort/{sortBy}", "${service.df.endpoint}/sort/{sortBy}"}, method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String sort(@PathVariable String sortBy, final ModelMap modelMap, final HttpSession session) {
        PagedListHolder<Destination> pages = (PagedListHolder<Destination>) session.getAttribute("DestinationFinderController_result");
        switch (sortBy) {
            case "fare":
                Collections.sort(pages.getSource(), new CompareDestinationByFare());
                break;
            case "description":
                Collections.sort(pages.getSource(), new CompareDestinationByAirport());
                break;
            default:
                break;
        }
        modelMap.put("pages", pages);
        return "finder";
    }

    @ModelAttribute("req")
    public Request request() {
        return new Request();
    }

    @ModelAttribute("pages")
    public PagedListHolder<Destination> pagedListHolder() {
        return new PagedListHolder<>(new ArrayList<>());
    }

    @ModelAttribute("allOrigins")
    public List<Airport> allOrirings() {
        return airportService.findAll();
    }

}
