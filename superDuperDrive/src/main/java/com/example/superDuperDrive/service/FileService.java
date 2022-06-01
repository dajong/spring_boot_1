package com.example.superDuperDrive.service;

import com.example.superDuperDrive.mapper.FileMapper;
import com.example.superDuperDrive.model.File;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class FileService {
  private FileMapper fileMapper;

  public FileService(FileMapper fileMapper) {
    this.fileMapper = fileMapper;
  }

  @PostConstruct
  public void postConstruct(){
    System.out.println("Creating FileService bean");
  }

  public void addFile(File file){
    fileMapper.uploadFile(file);
  }

  public List<File> getAllFiles(){
    return fileMapper.getAllFiles();
  }
}
