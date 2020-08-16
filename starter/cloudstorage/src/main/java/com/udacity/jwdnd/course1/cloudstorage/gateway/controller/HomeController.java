package com.udacity.jwdnd.course1.cloudstorage.gateway.controller;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;
import com.udacity.jwdnd.course1.cloudstorage.domain.File;
import com.udacity.jwdnd.course1.cloudstorage.domain.Note;
import com.udacity.jwdnd.course1.cloudstorage.gateway.CredentialRequestConverter;
import com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request.CredentialRequest;
import com.udacity.jwdnd.course1.cloudstorage.services.credential.CreateCredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.credential.FindCredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.file.FindFilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.note.FindNotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final FindFilesService findFilesService;
    private final FindNotesService findNotesService;
    private final FindCredentialsService findCredentialsService;
    private final CreateCredentialService createCredentialService;

    private final CredentialRequestConverter credentialRequestConverter;

    public HomeController(FindFilesService findFilesService, FindNotesService findNotesService, FindCredentialsService findCredentialsService, CreateCredentialService createCredentialService, CredentialRequestConverter credentialRequestConverter) {
        this.findFilesService = findFilesService;
        this.findNotesService = findNotesService;
        this.findCredentialsService = findCredentialsService;
        this.createCredentialService = createCredentialService;
        this.credentialRequestConverter = credentialRequestConverter;
    }

    @GetMapping
    public String getHome(final Authentication authentication, final Model model){
        final String userName = authentication.getPrincipal().toString();

        final List<File> files = findFilesService.execute(userName);
        model.addAttribute("files", files);

        final List<Note> notes = findNotesService.execute(userName);
        model.addAttribute("notes", notes);

        final List<CredentialRequest> credentialList = credentialRequestConverter.convert(findCredentialsService.execute(userName));
        model.addAttribute("credentialList", credentialList);

        return "home";
    }

    @PostMapping(value = "/credentials/create")
    public String createCredential(final Authentication authentication, final CredentialRequest credentialRequest, final Model model){
        final String userName = authentication.getPrincipal().toString();

        try {
            createCredentialService.execute(credentialRequest.toCredentialDomain(), userName);
            model.addAttribute("credentialsSuccess", true);
        } catch (final Exception ex){
                model.addAttribute("credentialError", true);
            model.addAttribute("credentialsErrorMessage", ex.getMessage());
        }

        return "redirect:/home";
    }
}
