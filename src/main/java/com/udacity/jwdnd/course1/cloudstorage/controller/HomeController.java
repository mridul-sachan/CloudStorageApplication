package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsEntity;
import com.udacity.jwdnd.course1.cloudstorage.model.FileEntity;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.CreateNotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
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
    @Autowired
    private CreateNotesService createNotesService;
    @Autowired
    private CredentialService credentialService;


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

    @PostMapping("/createNotes")
    public String notesController(@ModelAttribute("userNotesObject") NotesEntity userNotesObject, Authentication auth){
        Integer UID = userService.getuid(auth.getName()) ;
        userNotesObject.setUserId(UID);
        createNotesService.createNewNote(userNotesObject);

        return "result";
    }

    @PostMapping("/addCredentials")
    public String credentialController(@ModelAttribute("userNotesObject") CredentialsEntity credentials, Authentication auth){
        Integer UID = userService.getuid(auth.getName()) ;
        credentials.setUserId(UID);
        credentialService.createNewCredential(credentials);

        return "result";
    }
}
