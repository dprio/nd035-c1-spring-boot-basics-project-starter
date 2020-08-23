package com.udacity.jwdnd.course1.cloudstorage.gateway.controller;

import com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request.CredentialRequest;
import com.udacity.jwdnd.course1.cloudstorage.services.credential.CreateCredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.credential.DeleteCredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.credential.UpdateCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    @Autowired
    private CreateCredentialService createCredentialService;
    @Autowired
    private UpdateCredentialService updateCredentialService;
    @Autowired
    private DeleteCredentialService deleteCredentialService;


    @PostMapping(value = "/create")
    public ModelAndView createCredential(final Authentication authentication, final CredentialRequest credentialRequest){
        final String userName = authentication.getPrincipal().toString();
        try {
            if(Objects.isNull(credentialRequest.getCredentialId())) {
                createCredentialService.execute(credentialRequest.toCredentialDomain(), userName);
            }else {
                updateCredentialService.execute(credentialRequest.toCredentialDomain(), userName);
            }
            return new ModelAndView("redirect:/result?success=true&redirect=/home?credential=true");
        } catch (final Exception ex){
            return new ModelAndView( "redirect:/result?redirect=/home?credential=true&errorMessage=" + ex.getMessage());
        }
    }

    @RequestMapping(value = "/{credentialId}/delete")
    public ModelAndView deleteCredential(final Authentication authentication, final @PathVariable("credentialId") int credentialId){
        final String userName = authentication.getPrincipal().toString();

        deleteCredentialService.execute(credentialId, userName);

        return new ModelAndView("redirect:/result?success=true&redirect=/home?credential=true");
    }

}
