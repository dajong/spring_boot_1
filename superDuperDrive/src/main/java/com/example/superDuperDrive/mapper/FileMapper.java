package com.example.superDuperDrive.mapper;

import com.example.superDuperDrive.model.File;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {
  @Select("SELECT * FROM FILES")
  List<File> getAllFiles();

  @Insert(
      "INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
  @Options(useGeneratedKeys = true, keyProperty = "fileId")
  int uploadFile(File file);

  @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
  int removeFile(String filename);
}
