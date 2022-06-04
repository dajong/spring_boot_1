package com.example.superDuperDrive.mapper;

import com.example.superDuperDrive.model.Note;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NoteMapper {

  @Select("SELECT * from NOTES WHERE userId = #{userId}")
  ArrayList<Note> findUserNote(String noteTitle, int userId);

  @Select("SELECT * from NOTES WHERE ntoetitle = #{noteTitle} AND userId = #{userId}")
  Note findNote(String noteTitle, int userId);

  @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
  int deleteNote(int noteId);

  @Insert(
      "INSERT INTO NOTES (notetitle, notedescription, userId) VALUES(#{noteTitle}, #{noteDescription}, #{userId} )")
  @Options(useGeneratedKeys = true, keyProperty = "noteId")
  int addNote(Note note);
}
