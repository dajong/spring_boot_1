package com.example.superDuperDrive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Credential {
  private String credentialId;
  private String url;
  private String username;
  private String password;
  private int userId;
}
