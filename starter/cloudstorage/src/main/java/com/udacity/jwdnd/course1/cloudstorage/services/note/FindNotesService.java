package com.udacity.jwdnd.course1.cloudstorage.services.note;

import com.udacity.jwdnd.course1.cloudstorage.domain.Note;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.NoteGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FindNotesService {

    private final NoteGateway noteGateway;
    private final UserGateway userGateway;

    public FindNotesService(NoteGateway noteGateway, UserGateway userGateway) {
        this.noteGateway = noteGateway;
        this.userGateway = userGateway;
    }


    public List<Note> execute(final String userName){
        return userGateway.findUserByUserName(userName)
                .map(user -> noteGateway.findNotesByUserId(user.getUserId()))
                .orElse(Collections.emptyList());
    }
}
