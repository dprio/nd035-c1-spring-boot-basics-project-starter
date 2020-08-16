package com.udacity.jwdnd.course1.cloudstorage.gateway.controller;

import com.udacity.jwdnd.course1.cloudstorage.domain.Note;
import com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request.NoteRequest;
import com.udacity.jwdnd.course1.cloudstorage.services.note.CreateNoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.note.FindNotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.note.UpdateNoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final CreateNoteService createNoteService;
    private final FindNotesService findNotesService;
    private final UpdateNoteService updateNoteService;

    public NoteController(CreateNoteService createNoteService, FindNotesService findNotesService, UpdateNoteService updateNoteService) {
        this.createNoteService = createNoteService;
        this.findNotesService = findNotesService;
        this.updateNoteService = updateNoteService;
    }

    @PostMapping(value = "/create")
    public String crateNote(final Authentication authentication, final NoteRequest noteRequest, final Model model){
        final String userName = authentication.getPrincipal().toString();

        try {
            if (Objects.isNull(noteRequest.getNoteId())) {
                createNoteService.execute(noteRequest.toNoteDomain(), userName);
            }else{
                updateNoteService.execute(noteRequest.toNoteDomain(), userName);
            }
            model.addAttribute("noteSuccess", true);
        } catch (final Exception ex){
            model.addAttribute("noteError", true);
            model.addAttribute("noteErrorMessage", ex.getMessage());
        }

        return "redirect:/home";
    }

    @GetMapping
    public String findNotes(final Authentication authentication, final Model model){
        final String userName = authentication.getPrincipal().toString();

        final List<Note> notes = findNotesService.execute(userName);
        model.addAttribute("notes", notes);

        return "redirect:/home";
    }
}
