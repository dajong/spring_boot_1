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
      "INSERT INTO CREDENTIALS(userId, url, password, key, user) VALUES(#{userId}, #{url}, #{password}, #{key}, #{user})")
  @Options(useGeneratedKeys = true, keyProperty = "credentialId")
  int addCredential(Credential credential);

  @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
  Credential getCredentialbyId(Integer credentialId);

  @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
  ArrayList<Credential> getAllCredentials(Integer userId);

  @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
  int deleteCredential(Integer credentialId);

  @Update(
      "UPDATE CREDENTIALS SET url = #{url}, password = #{password}, key = #{key}, user = #{user} WHERE credentialId = #{credentialId}")
  int updateCredential(Credential credentialModel);
}
