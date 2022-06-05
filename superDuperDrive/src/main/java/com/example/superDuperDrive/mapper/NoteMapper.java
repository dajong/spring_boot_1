package com.example.superDuperDrive.mapper;

import com.example.superDuperDrive.model.Note;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NoteMapper {

  @Select("SELECT * from NOTES WHERE userId = #{userId}")
  ArrayList<Note> getUserNote(Integer userId);

  @Select("SELECT * from NOTES WHERE noteId = #{noteId} AND userId = #{userId}")
  Note getNoteById(Integer noteId, Integer userId);

  @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
  Integer deleteNote(Integer noteId);

  @Insert(
      "INSERT INTO NOTES (notetitle, notedescription, userId) VALUES(#{noteTitle}, #{noteDescription}, #{userId} )")
  @Options(useGeneratedKeys = true, keyProperty = "noteId")
  Integer addNote(Note note);

  @Update(
      "UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteId = #{noteId}")
  int updateNote(Note note);
}
