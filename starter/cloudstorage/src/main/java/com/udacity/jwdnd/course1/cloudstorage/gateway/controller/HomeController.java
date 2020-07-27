package com.udacity.jwdnd.course1.cloudstorage.gateway.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String getHome(){
        return "home";
    }

    @PostMapping(
            value = "/file/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String uploadFile(final MultipartFile fileUpload){
        return "home";
    }
}
