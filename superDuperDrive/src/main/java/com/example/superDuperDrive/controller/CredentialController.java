package com.example.superDuperDrive.controller;

import com.example.superDuperDrive.model.Credential;
import com.example.superDuperDrive.model.User;
import com.example.superDuperDrive.service.CredentialService;
import com.example.superDuperDrive.service.EncryptionService;
import com.example.superDuperDrive.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credential")
public class CredentialController {

  public CredentialController(
      CredentialService credentialService,
      EncryptionService encryptionService,
      UserService userService) {
    this.credentialService = credentialService;
    this.encryptionService = encryptionService;
    this.userService = userService;
  }

  private CredentialService credentialService;
  private EncryptionService encryptionService;
  private UserService userService;

  @PostMapping("/add")
  public String addCredential(
      Authentication authentication, @ModelAttribute("credent") Credential credent, Model model) {
    User user = userService.getUser(authentication.getName());
    Integer userId = user.getUserId();
    Credential oldCredential = credentialService.getCredentialById(credent.getCredentialId());
    try {
      if (oldCredential != null) {
        oldCredential.setPassword(credent.getPassword());
        oldCredential.setUrl(credent.getUrl());
        oldCredential.setUsername(credent.getUsername());
        credentialService.updateCredentials(oldCredential);
      } else {
        credentialService.addCredentials(
            new Credential(
                null,
                credent.getUrl(),
                credent.getUsername(),
                null,
                credent.getPassword(),
                userId));
      }
      model.addAttribute("uploadSuccess", "tempKey");
    } catch (Exception e) {
      e.printStackTrace();
      // model.addAttribute("uploadFailure", "tempKey");
    }

    return "result";
  }

  @GetMapping("/delete/{credential-id}")
  public String deleteCredential(
      Authentication authentication,
      @PathVariable("credential-id") Integer credentialId,
      Model model) {
    User user = userService.getUser(authentication.getName());
    Integer userId = user.getUserId();
    Credential credentialToBeDeleted = credentialService.getCredentialById(credentialId);

    try {
      credentialService.deleteCredential(credentialToBeDeleted);
      model.addAttribute("deleteSuccess", "tempKey");
    } catch (Exception e) {
      model.addAttribute("uploadFailure", "tempKey");
    }
    //    if(credentialToBeDeleted != null){
    //      credentialService.deleteCredential(credentialToBeDeleted);
    //      model.addAttribute("uploadFailure", "tempKey");
    //    }

    return "result";
  }
}
