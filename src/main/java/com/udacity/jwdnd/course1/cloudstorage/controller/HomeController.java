package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.FileEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private  UserService userService;

    @Autowired
    private  FileUploadService fileUploadService;


    @RequestMapping("/home")
    public  String getHomePage(Authentication auth, Model model)
    {
        Integer UID = userService.getuid(auth.getName()) ;
        List<FileEntity> files = fileUploadService.getAllFiles(UID);
        model.addAttribute("files",files);
        return "home";
    }
}
