package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Login {

    @GetMapping("/login")
    public  String getLoginPage()
    {
        System.out.println("Inside Get Login Controller");
        return "login";
    }

}
