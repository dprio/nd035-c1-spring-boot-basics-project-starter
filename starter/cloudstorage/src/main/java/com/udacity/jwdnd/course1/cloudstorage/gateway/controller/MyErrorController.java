package com.udacity.jwdnd.course1.cloudstorage.gateway.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class MyErrorController implements ErrorController {

    @GetMapping
    public ModelAndView getHome(){
        return new ModelAndView("redirect:/home");
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
