package com.udacity.jwdnd.course1.cloudstorage.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/result")
public class ResultController {

    @GetMapping
    public String getResult(
            @RequestParam(required = false) final Boolean success,
            @RequestParam(required = false) final Boolean error,
            @RequestParam(required = false) final String errorMessage,
            @RequestParam final String redirect,
            final Model model)
    {
        model.addAttribute("success", success);
        model.addAttribute("error", error);
        model.addAttribute("errorMessage",errorMessage);
        model.addAttribute("redirect",redirect);

        return "result";
    }


}
