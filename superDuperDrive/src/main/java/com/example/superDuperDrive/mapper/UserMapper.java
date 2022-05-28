package com.example.superDuperDrive.mapper;

import com.example.superDuperDrive.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE email = #{email}")
    User getUser(String email);

    @Insert("INSERT INTO users (firstName, lastName, email, mobileNumber, password, salt) VALUES (#{firstName}, #{lastName}, #{email}, #{mobileNumber}, #{password}, #{salt}")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);
}
