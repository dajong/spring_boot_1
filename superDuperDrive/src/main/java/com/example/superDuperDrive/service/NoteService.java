package com.example.superDuperDrive.service;

import com.example.superDuperDrive.mapper.NoteMapper;
import com.example.superDuperDrive.model.Note;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
  NoteMapper noteMapper;

  public NoteService(NoteMapper noteMapper) {
    this.noteMapper = noteMapper;
  }

  @PostConstruct
  public void postConstruct(){
    System.out.println("Creating NoteService bean");
  }

  public void addNotes(Note note){
    noteMapper.addNote(note);
  }

  public List<Note> getAllNotes(){
    return noteMapper.getAllNotes();
  }

}
