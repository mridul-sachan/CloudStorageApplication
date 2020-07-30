package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.FileEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class UploadController {

    private final UserService userService;
    private final FileUploadService fileUploadService;

    public UploadController(UserService userService, FileUploadService fileUploadService) {
        this.userService = userService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/upload")
    public String getFiles(@RequestParam("fileUpload") MultipartFile fileUpload,Authentication auth, Model model)
    {
        //Getting userid of current logged in User
        Integer UID = userService.getuid(auth.getName()) ;
        List<FileEntity> lt = fileUploadService.getAllFiles(UID);
        System.out.println("FILE DETAILS --------");
        for(int i = 0; i<lt.size();i++)
        {
            System.out.println("i"+lt.get(i).getFileName());
        }
        //model.addAttribute("files",fileUploadService.getAllFiles(UID));
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
