package com.example.superDuperDrive.controller;

import com.example.superDuperDrive.config.Messages;
import com.example.superDuperDrive.model.Note;
import com.example.superDuperDrive.model.User;
import com.example.superDuperDrive.service.NoteService;
import com.example.superDuperDrive.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("notes")
public class NoteController {
  private UserService userService;
  private NoteService noteService;

  public NoteController(UserService userService, NoteService noteService) {
    this.userService = userService;
    this.noteService = noteService;
  }

  @PostMapping("/addnote")
  public String addNote(
      Authentication authentication, @ModelAttribute("newNote") Note newNote, Model model) {
    String username = authentication.getName();
    User user = userService.getUser(username);

    Note noteExist = noteService.getNoteById(newNote.getNoteId(), user.getUserId());
    try {
      if (noteExist != null) {
        noteService.updateNote(newNote);
        model.addAttribute("uploadSuccess", Messages.NOTE_UPLOAD_SUCCESS_MESSAGE);
      } else {
        noteService.addNotes(
            new Note(null, newNote.getNoteTitle(), newNote.getNoteDescription(), user.getUserId()));
        model.addAttribute("uploadSuccess", Messages.NOTE_UPLOAD_SUCCESS_MESSAGE);
        return "result";
      }

    } catch (Exception e) {
      model.addAttribute("uploadFailure", Messages.NOTE_UPLOAD_FAILURE_MESSAGE);
      return "result";
    }
    return "result";
  }

  //  @PostMapping("/updateNote/{note_id}")
  //  public String updateNote(
  //      @PathVariable("note_id") Integer noteId,
  //      Authentication authentication,
  //      @ModelAttribute("updateNote") NoteForm updateNote,
  //      Model model) {
  //    String username = authentication.getName();
  //    User user = userService.getUser(username);
  //    Note oldNote = noteService.getNoteById(noteId, user.getUserId());
  //    oldNote.setNoteTitle(updateNote.getTitle());
  //    oldNote.setNoteDescription((updateNote.getDescription()));
  //    try {
  //      noteService.updateNote(oldNote);
  //      model.addAttribute("deleteSuccess", Messages.NOTE_DELETION_SUCCESSFUL_MESSAGE);
  //    } catch (Exception e) {
  //      model.addAttribute("deleteSuccess", Messages.NOTE_DELETION_FAILURE_MESSAGE);
  //      e.printStackTrace();
  //    }
  //
  //    return "result";
  //  }

  @GetMapping("/delete/{note_id}")
  public String deleteFile(@PathVariable("note_id") Integer noteId, Model model) {
    try {
      noteService.deleteNote(noteId);
      model.addAttribute("deleteSuccess", Messages.NOTE_DELETION_SUCCESSFUL_MESSAGE);
    } catch (Exception e) {
      model.addAttribute("deleteSuccess", Messages.NOTE_DELETION_FAILURE_MESSAGE);
    }
    return "result";
  }
}
