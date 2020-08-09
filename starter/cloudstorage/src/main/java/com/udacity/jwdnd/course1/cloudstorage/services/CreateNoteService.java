package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.domain.Note;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.NoteGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import org.springframework.stereotype.Service;

@Service
public class CreateNoteService {

    private final NoteGateway noteGateway;
    private final UserGateway userGateway;

    public CreateNoteService(NoteGateway noteGateway, UserGateway userGateway) {
        this.noteGateway = noteGateway;
        this.userGateway = userGateway;
    }

    public Integer execute(final Note note, final String username){

        return userGateway.findUserByUserName(username)
                .map(user -> {
                    final Note newNote =  new Note(
                            null,
                            note.getNoteTitle(),
                            note.getNoteDescription(),
                            user.getUserId()
                    );
                    return noteGateway.insertNote(newNote);
                }).orElseThrow(() -> new RuntimeException("User not found !"));

    }
}
