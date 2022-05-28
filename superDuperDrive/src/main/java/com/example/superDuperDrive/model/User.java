package com.example.superDuperDrive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class User {
  private String userId;
  private String firstName;
  private String lastName;
  private String email;
  private String salt;
  private String password;
  private String mobileNumber;
}
