package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.FileEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



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

    @RequestMapping("/result")
    public String result() {
        return "result";
    }

    @GetMapping("/file/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId) {
        fileUploadService.deleteFile(fileId); return "result";
    }

    @GetMapping("/file/view/{fileId}")
    public ResponseEntity<Resource> getFile(@PathVariable Integer fileId)  {
        FileEntity file = fileUploadService.getFilebyId(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                + file.getFileName() + "\"").body(new
                ByteArrayResource(file.getFileData()));
    }
}
