package com.example.superDuperDrive.mapper;

import com.example.superDuperDrive.model.Credential;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CredentialMapper {
  @Insert(
      "INSERT INTO CREDENTIALS(username, url, passwordKey, password, userId) VALUES(#{username}, #{url}, #{passwordKey}, #{password}, #{userId})")
  @Options(useGeneratedKeys = true, keyProperty = "credentialId")
  int addCredential(Credential credential);

  @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
  Credential getCredentialById(Integer credentialId);

  @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
  ArrayList<Credential> getAllCredentials(Integer userId);

  @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
  int deleteCredential(Integer credentialId);

  @Update(
      "UPDATE CREDENTIALS SET username = #{username}, url = #{url}, password = #{password}, passwordKey = #{passwordKey}, userId = #{userId} WHERE credentialId = #{credentialId}")
  int updateCredential(Credential credential);
}
