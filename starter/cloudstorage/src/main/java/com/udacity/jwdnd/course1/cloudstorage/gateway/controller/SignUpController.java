package com.udacity.jwdnd.course1.cloudstorage.gateway.controller;

import com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request.SignUpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @GetMapping
    public String getSignup(){
        return "signup";
    }

    @PostMapping
    public String signUpUser(final SignUpRequest signUpRequest){

        System.out.println(signUpRequest);

        return "login";
    }

}
