package com.example.superDuperDrive.mapper;

import com.example.superDuperDrive.model.Note;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NoteMapper {
  @Select("SELECT * FROM NOTES")
  List<Note> getAllNotes();



  @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId) VALUES(#{noteTitle}, #{noteDescription}, #{userId} )")
  @Options(useGeneratedKeys = true, keyProperty = "noteId")
  int addNote(Note note);

}
