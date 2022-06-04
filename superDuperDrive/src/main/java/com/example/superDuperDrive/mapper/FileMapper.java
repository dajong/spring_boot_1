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
  @Select("SELECT * FROM FILES WHERE userId = #{userId}")
  List<File> getAllFiles(Integer userId);

  @Select("SELECT * FROM FILES WHERE filename = #{fileName} AND userId = #{userId}")
  File getFile(String fileName, Integer userId);

  @Insert(
      "INSERT INTO FILES (filename, contenttype, filesize, userId, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
  @Options(useGeneratedKeys = true, keyProperty = "fileId")
  int uploadFile(File file);

  @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
  boolean removeFile(int fileId);
}
