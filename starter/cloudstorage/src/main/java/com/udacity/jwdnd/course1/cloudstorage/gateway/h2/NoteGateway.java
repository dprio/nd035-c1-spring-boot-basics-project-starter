package com.udacity.jwdnd.course1.cloudstorage.gateway.h2;

import com.udacity.jwdnd.course1.cloudstorage.domain.Note;

import java.util.List;

public interface NoteGateway {

    List<Note> findNotesByUserId(int userId);

    int insertNote(Note note);
}
