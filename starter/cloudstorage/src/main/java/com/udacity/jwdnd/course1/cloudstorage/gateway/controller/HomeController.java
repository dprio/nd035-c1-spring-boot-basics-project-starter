package com.udacity.jwdnd.course1.cloudstorage.gateway.controller;

import com.udacity.jwdnd.course1.cloudstorage.domain.File;
import com.udacity.jwdnd.course1.cloudstorage.domain.Note;
import com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request.NoteRequest;
import com.udacity.jwdnd.course1.cloudstorage.services.CreateNoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.FindFilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.FindNotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UploadFileService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UploadFileService uploadFileService;
    private final FindFilesService findFilesService;
    private final FindNotesService findNotesService;
    private final CreateNoteService createNoteService;

    public HomeController(UploadFileService uploadFileService, FindFilesService findFilesService, FindNotesService findNotesService, CreateNoteService createNoteService) {
        this.uploadFileService = uploadFileService;
        this.findFilesService = findFilesService;
        this.findNotesService = findNotesService;
        this.createNoteService = createNoteService;
    }

    @GetMapping
    public String getHome(final Authentication authentication, final Model model){
        final String userName = authentication.getPrincipal().toString();

        final List<File> files = findFilesService.execute(userName);
        model.addAttribute("files", files);

        final List<Note> notes = findNotesService.execute(userName);
        model.addAttribute("notes", notes);

        return "home";
    }

    @PostMapping(
            value = "/file/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String uploadFile(final Authentication authentication, final MultipartFile fileUpload, final Model model){
        final String userName = authentication.getPrincipal().toString();

        try {
            uploadFileService.execute(fileUpload, userName);
            model.addAttribute("fileSuccess", true);
        }catch (final Exception exception){
            model.addAttribute("fileError", true);
            model.addAttribute("fileErrorMessage", exception.getMessage());
        }

        return "home";
    }

    @PostMapping(value = "/notes/create")
    public String crateNote(final Authentication authentication, final NoteRequest noteRequest, final Model model){
        final String userName = authentication.getPrincipal().toString();

        try {
            createNoteService.execute(noteRequest.toNoteDomain(), userName);
            model.addAttribute("noteSuccess", true);
        } catch (final Exception ex){
            model.addAttribute("noteError", true);
            model.addAttribute("noteErrorMessage", ex.getMessage());
        }

        return "home";
    }
}
