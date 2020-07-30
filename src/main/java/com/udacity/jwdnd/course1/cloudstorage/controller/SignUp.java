package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUp {

    private final UserService userService;

    public SignUp(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public  String getSignUpPage(@ModelAttribute("userFormDataObject") User userFormDataObject)
    {
        System.out.println("Inside SignUP Controller");
        return "signup";
    }

    @PostMapping("/signup")
    public  String getUserFormData(@ModelAttribute("userFormDataObject") User userFormDataObject, Model model) {

        String signupError = null;

        if (!userService.isUserNameAvailable(userFormDataObject.getUsername())) {
            signupError = "The username already exists.";
        }
        if (signupError == null) {
            int rowsAdded = userService.createUser(userFormDataObject);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}
