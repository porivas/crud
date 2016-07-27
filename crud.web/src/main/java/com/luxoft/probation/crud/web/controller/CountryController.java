package com.luxoft.probation.crud.web.controller;

import com.luxoft.probation.crud.core.domain.Country;
import com.luxoft.probation.crud.service.GEOService;
import com.luxoft.probation.crud.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Admin module country controller
 * <p>
 * Created by hhayryan on 5/31/2016.
 */
@Controller
@RequestMapping(value = "/country/*")
public class CountryController extends BaseController {

    @Autowired
    GEOService geoService;


    @RequestMapping("/countries/{from}/{to}")
    public ModelAndView getCountriesByPage(@PathVariable("from") int from, @PathVariable("to") int to) {
        ModelAndView model = new ModelAndView(viewJSON);

        List<Country> countries = geoService.getCountriesByPage(from, to);

        if (countries != null && !countries.isEmpty()) {
            model.getModel().put("countries", countries);
        } else {
            model.getModel().put("message", "There is no available country data.");
        }

        return model;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/create_list")
    public ModelAndView createCountryList(@RequestBody List<Country> countries) {
        ModelAndView model = new ModelAndView(viewJSON);

        geoService.createCountryBatch(countries);
        model.getModel().put(JSON_MESSAGE, JSON_OK);
        return model;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/create")
    public ModelAndView createCountry(@RequestBody Country country) {
        ModelAndView model = new ModelAndView(viewJSON);

        geoService.createCountry(country);

        model.getModel().put(JSON_MESSAGE, JSON_OK);

        return model;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find/{name}")
    public ModelAndView findCitiesByName(@PathVariable("name") String name) {
        ModelAndView model = new ModelAndView(viewJSON);
        List<Country> countries = geoService.findCountryByName(name.replace("*", "%"));

        model.getModel().put("countries", countries);

        return model;
    }

    public void setGeoService(GEOService geoService) {
        this.geoService = geoService;
    }
}
