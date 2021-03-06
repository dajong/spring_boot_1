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
public class Note {
  private Integer noteId;
  private String noteTitle;
  private String noteDescription;
  private Integer userId;
}
