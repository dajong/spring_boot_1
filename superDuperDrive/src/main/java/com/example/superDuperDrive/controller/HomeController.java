package com.example.superDuperDrive.controller;

import com.example.superDuperDrive.config.Messages;
import com.example.superDuperDrive.model.File;
import com.example.superDuperDrive.model.Note;
import com.example.superDuperDrive.model.User;
import com.example.superDuperDrive.service.FileService;
import com.example.superDuperDrive.service.NoteService;
import com.example.superDuperDrive.service.UserService;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/home")
public class HomeController {
  private FileService fileService;
  private UserService userService;
  private NoteService noteService;

  public HomeController(FileService fileService, UserService userService, NoteService noteService) {
    this.fileService = fileService;
    this.userService = userService;
    this.noteService = noteService;
  }

  @GetMapping
  public String getHomePage(
      @ModelAttribute("newNote") Note newNote, Authentication authentication, Model model) {
    User user = userService.getUser(authentication.getName());

    model.addAttribute("allFiles", this.fileService.getAllFiles(user.getUserId()));
    model.addAttribute("allUserNotes", this.noteService.getAllNotes(user.getUserId()));
    return "home";
  }

  @PostMapping("/file/upload")
  public String uploadFile(
      @RequestParam("fileUpload") MultipartFile multipartFile,
      Authentication authentication,
      Model model) {
    User user = userService.getUser(authentication.getName());
    Integer userId = user.getUserId();
    if (multipartFile.isEmpty()) {
      model.addAttribute("errorMessage", Messages.FILE_NOT_SELECTED_MESSAGE);
    } else if (fileService.fileNameExist(userId, multipartFile.getOriginalFilename())) {
      model.addAttribute("sameNameExists", Messages.FILE_NAME_EXISTED_MESSAGE);
    } else {
      try {
        fileService.uploadFile(multipartFile, userId);
        model.addAttribute("uploadSuccess", Messages.FILE_UPLOAD_SUCCESS_MESSAGE);
      } catch (IOException e) {
        // e.printStackTrace();
        model.addAttribute("uploadFailure", Messages.FILE_UPLOAD_FAILURE_MESSAGE);
      }
    }

    return "result";
  }

  @GetMapping("/file/download/{file_name}")
  public ResponseEntity<byte[]> downloadFile(
      @PathVariable("file_name") String filename, Authentication authentication) {
    String username = authentication.getName();
    User user = userService.getUser(username);

    File file = fileService.getFileByName(filename.replace('%', ' '), user.getUserId());

    return ResponseEntity.ok()
        .header(
            HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
        .body(file.getFileData());
  }

  @GetMapping("/file/delete/{file_id}")
  public String deleteFile(@PathVariable("file_id") int fileId, Model model) {
    try {
      fileService.removeFile((fileId));
      model.addAttribute("deleteSuccess", Messages.FILE_DELETION_SUCCESSFUL_MESSAGE);
    } catch (Exception e) {
      model.addAttribute("deleteSuccess", Messages.FILE_DELETION_FAILURE_MESSAGE);
    }
    return "result";
  }
}
