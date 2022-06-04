package com.example.superDuperDrive.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class File {

  public File(
      Integer fileId,
      String fileName,
      String contentType,
      String fileSize,
      Integer userId,
      byte[] fileData) {
    this.fileId = fileId;
    this.fileName = fileName;
    this.contentType = contentType;
    this.fileSize = fileSize;
    this.userId = userId;
    this.fileData = fileData;
  }

  private Integer fileId;
  private String fileName;
  private String contentType;
  private String fileSize;
  private Integer userId;
  private byte[] fileData;
}
