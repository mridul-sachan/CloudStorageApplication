package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.FileEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UploadController {

    @Autowired
    private  UserService userService;

    @Autowired
    private  FileUploadService fileUploadService;

//    public UploadController(UserService userService, FileUploadService fileUploadService) {
//        this.userService = userService;
//        this.fileUploadService = fileUploadService;
//    }

    @GetMapping("/upload")
    public String getFiles(@RequestParam("fileUpload") MultipartFile fileUpload)
    {
        return "home";
    }


    @PostMapping("/upload")
        public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload,Authentication auth, Model model) throws IOException {
        Integer UID = userService.getuid(auth.getName()) ;
        String fs = String.valueOf(fileUpload.getSize());
        byte fileData[] = fileUpload.getBytes();

        FileEntity fileEntity = new FileEntity(fileUpload.getOriginalFilename(),fileUpload.getContentType(),fs,UID,fileData);
        fileUploadService.addFile(fileEntity);

       return "home";
    }

}
