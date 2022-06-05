package com.example.superDuperDrive.service;

import com.example.superDuperDrive.mapper.CredentialMapper;
import com.example.superDuperDrive.model.Credential;
import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

  public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
    this.credentialMapper = credentialMapper;
    this.encryptionService = encryptionService;
  }

  private CredentialMapper credentialMapper;
  private EncryptionService encryptionService;

  public int addCredentials(Credential credential) {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    String encodedSalt = Base64.getEncoder().encodeToString(salt);
    String encryptedPassword =
        encryptionService.encryptValue(credential.getPassword(), encodedSalt);
    return credentialMapper.addCredential(
        new Credential(
            null,
            credential.getUrl(),
            credential.getUsername(),
            encryptedPassword,
            credential.getUserId()));
  }
}
