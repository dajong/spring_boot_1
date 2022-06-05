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
  private Integer credentialId;
  private String url;
  private String username;
  private String passwordKey;
  private String password;
  private Integer userId;
}
