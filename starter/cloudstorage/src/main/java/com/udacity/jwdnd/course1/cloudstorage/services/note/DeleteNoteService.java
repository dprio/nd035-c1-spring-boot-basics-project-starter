package com.udacity.jwdnd.course1.cloudstorage.services.note;

import com.udacity.jwdnd.course1.cloudstorage.domain.User;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.NoteGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import org.springframework.stereotype.Service;

@Service
public class DeleteNoteService {

    private final NoteGateway noteGateway;
    private final UserGateway userGateway;

    public DeleteNoteService(NoteGateway noteGateway, UserGateway userGateway) {
        this.noteGateway = noteGateway;
        this.userGateway = userGateway;
    }

    public void execute(final int noteId, final String userName){
        int userId = userGateway.findUserByUserName(userName)
                .map(User::getUserId)
                .orElseThrow(() -> new RuntimeException("User not found !"));

        noteGateway.deleteNote(noteId, userId);
    }
}
