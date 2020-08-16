package com.udacity.jwdnd.course1.cloudstorage.services.note;

import com.udacity.jwdnd.course1.cloudstorage.domain.Note;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.NoteGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import org.springframework.stereotype.Service;

@Service
public class UpdateNoteService {

    private final NoteGateway noteGateway;
    private final UserGateway userGateway;

    public UpdateNoteService(NoteGateway noteGateway, UserGateway userGateway) {
        this.noteGateway = noteGateway;
        this.userGateway = userGateway;
    }

    public void execute(final Note note, final String userName){

        userGateway.findUserByUserName(userName)
                .map(user -> {
                    try {
                        final Note updateNote = new Note(
                                note.getNoteId(),
                                note.getNoteTitle(),
                                note.getNoteDescription(),
                                user.getUserId()
                        );

                        noteGateway.updateNote(updateNote);
                        return updateNote;
                    }catch (final Exception ex){
                        throw new RuntimeException("Note not found !");
                    }
                }).orElseThrow(() -> new RuntimeException("User not found !"));

    }
}
