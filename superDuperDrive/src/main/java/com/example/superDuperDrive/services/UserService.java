package com.example.superDuperDrive.services;

import com.example.superDuperDrive.mapper.UserMapper;
import com.example.superDuperDrive.model.User;
import java.security.SecureRandom;
import java.util.Base64;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UserService {
  private final UserMapper userMapper;
  private final HashService hashService;

  public boolean isEmailAvailable(String email){
    return userMapper.getUser(email) == null;
  }

  public int createUser(User user){
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    String encodedSalt = Base64.getEncoder().encodeToString(salt);
    String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
    return userMapper.insert(new User(null, user.getFirstName(), user.getLastName(), user.getEmail(), user.getMobileNumber(), user.getPassword(), user.getSalt()));
  }

  public User getUser(String email) {return userMapper.getUser(email);}
}
