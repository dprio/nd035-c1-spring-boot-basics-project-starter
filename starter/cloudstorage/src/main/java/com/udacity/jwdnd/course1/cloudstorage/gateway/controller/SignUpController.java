package com.udacity.jwdnd.course1.cloudstorage.gateway.controller;

import com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request.SignUpRequest;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignup(){
        return "signup";
    }

    @PostMapping
    public String signUpUser(final SignUpRequest signUpRequest, final Model model){
        try {
            userService.createUser(signUpRequest.toUserDomain());
            model.addAttribute("signupSuccess", true);
            return "signup";
        }catch (final Exception e){
            model.addAttribute("signupError", true);
            return "signup";
        }
    }

}
