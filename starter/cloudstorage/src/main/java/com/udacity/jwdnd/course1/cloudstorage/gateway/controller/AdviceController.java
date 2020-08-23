package com.udacity.jwdnd.course1.cloudstorage.gateway.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(MultipartException.class)
    public ModelAndView siceLimitAdvice(){
        final String message = "File exceeds size limit !";
        return new ModelAndView( "redirect:/result?redirect=/home?file=true&errorMessage=" + message);
    }
}
