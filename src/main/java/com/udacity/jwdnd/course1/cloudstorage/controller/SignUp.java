package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.UserFormData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUp {

    @GetMapping("/signup")
    public  String getLoginPage(@ModelAttribute("userFormDataObject") UserFormData userFormDataObject)
    {
        return "signup";
    }

    @PostMapping("/signup")
    public  String getUserFormData(@ModelAttribute("userFormDataObject") UserFormData userFormDataObject)
    {
        System.out.println("userFormDataObject :"+userFormDataObject.getFirstName() + " "+ userFormDataObject.getLastName());
        System.out.println(userFormDataObject.getUserName() + " " + userFormDataObject.getPassword());
        return "signup";
    }

}
