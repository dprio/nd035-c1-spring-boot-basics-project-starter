package com.udacity.jwdnd.course1.cloudstorage.gateway.controller;

import com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request.CredentialRequest;
import com.udacity.jwdnd.course1.cloudstorage.services.credential.CreateCredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.credential.DeleteCredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.credential.UpdateCredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    private final CreateCredentialService createCredentialService;
    private final UpdateCredentialService updateCredentialService;
    private final DeleteCredentialService deleteCredentialService;

    public CredentialController(CreateCredentialService createCredentialService, UpdateCredentialService updateCredentialService, DeleteCredentialService deleteCredentialService) {
        this.createCredentialService = createCredentialService;
        this.updateCredentialService = updateCredentialService;
        this.deleteCredentialService = deleteCredentialService;
    }

    @PostMapping(value = "/create")
    public String createCredential(final Authentication authentication, final CredentialRequest credentialRequest, final Model model){
        final String userName = authentication.getPrincipal().toString();

        try {
            if(Objects.isNull(credentialRequest.getCredentialId())) {
                createCredentialService.execute(credentialRequest.toCredentialDomain(), userName);
            }else {
                updateCredentialService.execute(credentialRequest.toCredentialDomain(), userName);
            }
            model.addAttribute("credentialsSuccess", true);
        } catch (final Exception ex){
            model.addAttribute("credentialError", true);
            model.addAttribute("credentialsErrorMessage", ex.getMessage());
        }

        return "redirect:/home";
    }

    @RequestMapping(value = "/{credentialId}/delete}")
    public String deleteCredential(final Authentication authentication, final @PathVariable("credentialId") int credentialId){
        final String username = authentication.getPrincipal().toString();

        deleteCredentialService.execute(credentialId, username);
        return "redirect:/home";
    }

}
