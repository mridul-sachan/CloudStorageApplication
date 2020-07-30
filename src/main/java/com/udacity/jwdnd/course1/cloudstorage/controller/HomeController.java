package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/home")
    public  String getHomePage()
    {
        System.out.println("Inside HomePage Controller");
        return "home";
    }

//// Lesson4 - 14
//    @PostMapping("/upload")
//    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {
//        InputStream fis = fileUpload.getInputStream();
//        System.out.println("Inside File Upload Controller"+fis.available());
//        return "home";
//    }

}
