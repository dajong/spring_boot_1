package com.example.superDuperDrive.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
  private int userId;
  private String firstName;
  private String lastName;
  private String username;
  private String salt;
  private String password;

  public User(
      Integer userId,
      String username,
      String salt,
      String password,
      String firstName,
      String lastName) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.salt = salt;
    this.password = password;
  }
}
