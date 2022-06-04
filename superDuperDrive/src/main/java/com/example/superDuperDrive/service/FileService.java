package com.example.superDuperDrive.service;

import com.example.superDuperDrive.mapper.FileMapper;
import com.example.superDuperDrive.model.File;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
  private final FileMapper fileMapper;

  public FileService(final FileMapper fileMapper) {
    this.fileMapper = fileMapper;
  }

  @PostConstruct
  public void postConstruct() {
    System.out.println("Creating FileService bean");
  }

  public void uploadFile(final MultipartFile file, final Integer userId) throws IOException {
    fileMapper.uploadFile(
        new File(
            null,
            file.getOriginalFilename(),
            file.getContentType(),
            sizeToString(file.getSize()),
            userId,
            file.getBytes()));
  }

  public List<File> getAllFiles(final Integer userId) {
    return fileMapper.getAllFiles(userId);
  }

  public void removeFile(final int fileId) {
    fileMapper.removeFile(fileId);
  }

  public File getFileByName(String fileName, Integer userId) {
    return fileMapper.getFile(fileName, userId);
  }

  public boolean fileNameExist(Integer userId, String filename) {
    File result = fileMapper.getFile(filename, userId);

    if (result == null) {
      return false;
    }

    return true;
  }

  private String sizeToString(final Long size) {
    final double l = Math.round(size / 1024L / 1024L);
    final double roundOff = (double) Math.round(l * 100) / 100;
    return roundOff + "mb";
  }
}
