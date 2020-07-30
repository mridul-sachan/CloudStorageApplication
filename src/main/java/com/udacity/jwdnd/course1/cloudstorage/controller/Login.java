package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login {

    @GetMapping("/login")
    public  String getLoginPage()
    {
        System.out.println("Inside Get Login Controller");
        return "login";
    }

}
