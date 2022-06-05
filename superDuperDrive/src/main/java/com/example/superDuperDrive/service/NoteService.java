package com.example.superDuperDrive.service;

import com.example.superDuperDrive.mapper.NoteMapper;
import com.example.superDuperDrive.model.Note;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
  NoteMapper noteMapper;

  public NoteService(NoteMapper noteMapper) {
    this.noteMapper = noteMapper;
  }

  public void addNotes(Note note) {
    noteMapper.addNote(note);
  }

  public void deleteNote(Integer id) {
    noteMapper.deleteNote(id);
  }

  public void updateNote(Note note) {
    noteMapper.updateNote(note);
  }

  public Note getNoteById(Integer noteId, Integer userId) {
    return noteMapper.getNoteById(noteId, userId);
  }

  public ArrayList<Note> getAllNotes(int userId) {
    return noteMapper.getUserNote(userId);
  }
}
