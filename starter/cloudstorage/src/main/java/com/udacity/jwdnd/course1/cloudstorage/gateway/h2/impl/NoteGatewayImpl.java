package com.udacity.jwdnd.course1.cloudstorage.gateway.h2.impl;

import com.udacity.jwdnd.course1.cloudstorage.domain.Note;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.NoteGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.repositories.NoteRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteGatewayImpl implements NoteGateway {

    private final NoteRepository noteRepository;

    public NoteGatewayImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findNotesByUserId(int userId) {
        return noteRepository.findNotesByUserId(userId);
    }

    @Override
    public int insertNote(Note note) {
        return noteRepository.insertNote(note);
    }
}
