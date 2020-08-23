package com.udacity.jwdnd.course1.cloudstorage.gateway.controller;

import com.udacity.jwdnd.course1.cloudstorage.domain.File;
import com.udacity.jwdnd.course1.cloudstorage.domain.Note;
import com.udacity.jwdnd.course1.cloudstorage.gateway.CredentialRequestConverter;
import com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request.CredentialRequest;
import com.udacity.jwdnd.course1.cloudstorage.services.credential.FindCredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.file.FindFilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.note.FindNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private FindFilesService findFilesService;
    @Autowired
    private FindNotesService findNotesService;
    @Autowired
    private FindCredentialsService findCredentialsService;
    @Autowired
    private CredentialRequestConverter credentialRequestConverter;


    @GetMapping
    public String getHome(
            final Authentication authentication,
            final Model model,
            final @RequestParam(required = false) String errorMessage){
        final String userName = authentication.getPrincipal().toString();

        model.addAttribute("errorMessage", errorMessage);

        final List<File> files = findFilesService.execute(userName);
        model.addAttribute("files", files);

        final List<Note> notes = findNotesService.execute(userName);
        model.addAttribute("notes", notes);

        final List<CredentialRequest> credentialList = credentialRequestConverter.convert(findCredentialsService.execute(userName));
        model.addAttribute("credentialList", credentialList);

        return "home";
    }
}
