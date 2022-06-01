package com.example.superDuperDrive.model;

import java.lang.reflect.Field;
import java.sql.Blob;
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
public class File {
  private int fileId;
  private String fileName;
  private String contentType;
  private String fileSize;
  private int userId;
  private Blob fileData;
}
