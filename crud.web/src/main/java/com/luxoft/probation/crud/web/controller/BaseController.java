package com.luxoft.probation.crud.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Base controller
 * <p>
 * Created by hhayryan on 6/1/2016.
 */
public class BaseController {

    public static final String JSON_MESSAGE = "message";
    public static final String JSON_OK = "OK";

    protected MappingJackson2JsonView viewJSON;

    public void setViewJSON(MappingJackson2JsonView viewJSON) {
        this.viewJSON = viewJSON;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView rmiErrorHadnler() {
        ModelAndView modelAndView = new ModelAndView(viewJSON);
        modelAndView.getModel().put("result", false);
        modelAndView.getModel().put("message", "Service is not available");
        return modelAndView;
    }
}
