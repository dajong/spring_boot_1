package com.example.superDuperDrive.controller;

import com.example.superDuperDrive.model.File;
import com.example.superDuperDrive.model.Note;
import com.example.superDuperDrive.service.FileService;
import com.example.superDuperDrive.service.NoteService;
import com.example.superDuperDrive.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class HomeController {
  private FileService fileService;
  private NoteService noteService;
  private UserService userService;

  public HomeController(FileService fileService,
      NoteService noteService, UserService userService) {
    this.fileService = fileService;
    this.noteService = noteService;
    this.userService = userService;
  }

  @GetMapping
  public String getHomePage(File file,  Note note, Model model){
    model.addAttribute("files",this.fileService.getAllFiles());
    model.addAttribute("notes", this.noteService.getAllNotes());
    return "home";
  }

  @PostMapping
  public String postFiles(Authentication authentication, File file, Model model) {
    file.setUserId(userService.getUser(authentication.getName()).getUserId());
    this.fileService.addFile(file);
    model.addAttribute("files", this.fileService.getAllFiles());
    return "home";
  }

}
