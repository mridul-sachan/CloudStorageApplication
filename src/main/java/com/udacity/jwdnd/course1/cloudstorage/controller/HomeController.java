package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsEntity;
import com.udacity.jwdnd.course1.cloudstorage.model.FileEntity;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
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


import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @Autowired
    private EncryptionService encryptionService;


    @RequestMapping("/home")
    public  String getHomePage(Authentication auth, Model model)
    {

        Integer UID = userService.getuid(auth.getName()) ;
        List<FileEntity> files = fileUploadService.getAllFiles(UID);
        List<NotesEntity> notes = createNotesService.getAllNotes(UID);
        List<CredentialsEntity> credentials = credentialService.getAllCredentials(UID);
        model.addAttribute("notes",notes);
        model.addAttribute("files",files);
        model.addAttribute("credentials",credentials);

        model.addAttribute("encryptionService", encryptionService);

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

        if(userNotesObject.getNoteId() != null) { createNotesService.updateNotes(userNotesObject); return "result"; }
        Integer UID = userService.getuid(auth.getName()) ;
        userNotesObject.setUserId(UID);
        createNotesService.createNewNote(userNotesObject);
        return "result";
    }

    @GetMapping("/note/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId) {
        createNotesService.deleteNote(noteId); return "result";
    }

    @PostMapping("/addCredentials")
    public String credentialController(@ModelAttribute("credentials") CredentialsEntity credentials, Authentication auth){

        if(credentials.getCredentialId() != null) { credentialService.updateCredential(credentials); return "result"; }
        Integer UID = userService.getuid(auth.getName()) ;
        credentials.setUserId(UID);
        credentialService.createNewCredential(credentials);
        return "result";
    }

    @GetMapping("/credential/delete/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId) {
        credentialService.deletecredential(credentialId); return "result";
    }

    @GetMapping(value = "/decode-password")
    @ResponseBody
    public Map<String, String> decodePassword(@RequestParam Integer credentialId){
        CredentialsEntity credential = credentialService.decodePassword(credentialId);
        String encryptedPassword = credential.getPassword();
        String encodedKey = credential.getKey();
        EncryptionService encryptionService = new EncryptionService();
        String decryptedPassword = encryptionService.decryptValue(encryptedPassword, encodedKey);
        Map<String, String> response = new HashMap<>();
        response.put("decryptedPassword", decryptedPassword);
        return response;
    }
}
