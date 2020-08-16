package com.udacity.jwdnd.course1.cloudstorage.gateway.h2.impl;

import com.udacity.jwdnd.course1.cloudstorage.domain.Note;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.NoteGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.repositories.NoteRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteGatewayImpl implements NoteGateway {

    private final NoteRepository noteRepository;

    public NoteGatewayImpl(final NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findNotesByUserId(final int userId) {
        return noteRepository.findNotesByUserId(userId);
    }

    @Override
    public int insertNote(final Note note) {
        return noteRepository.insertNote(note);
    }

    @Override
    public void updateNote(final Note note) {
        noteRepository.updateNote(note);
    }

    @Override
    public void deleteNote(final int noteId, final int userId) {
        noteRepository.deleteNote(noteId, userId);
    }
}
