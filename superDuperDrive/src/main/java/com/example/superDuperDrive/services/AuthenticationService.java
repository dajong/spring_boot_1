package com.example.superDuperDrive.services;

import com.example.superDuperDrive.mapper.UserMapper;
import com.example.superDuperDrive.model.User;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@AllArgsConstructor

public class AuthenticationService implements AuthenticationProvider {
  private UserMapper userMapper;
  private HashService hashService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();

    User user = userMapper.getUser(email);
    if(user != null){
      String encodedSalt = user.getSalt();
      String hashedPassword = hashService.getHashedValue(password, encodedSalt);

      if(user.getPassword().equals((hashedPassword))){
        return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
      }
    }

    return null;
  }

  @Override
  public boolean supports(Class<?> authentication) {

    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
