package com.example.superDuperDrive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@ToString

public class User {
  private String userId;
  private String firstName;
  private String lastName;
  private String username;
  private String salt;
  private String password;

  public User(String userId, String username, String salt, String password, String firstName, String lastName) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.salt = salt;
    this.password = password;
  }
}
