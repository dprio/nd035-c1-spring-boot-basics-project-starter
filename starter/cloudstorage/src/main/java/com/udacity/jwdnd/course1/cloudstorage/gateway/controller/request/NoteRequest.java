package com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request;

import com.udacity.jwdnd.course1.cloudstorage.domain.Note;

public class NoteRequest {

    private String noteId;
    private String noteTitle;
    private String noteDescription;


    public NoteRequest(String noteId, String noteTitle, String noteDescription) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    public Note toNoteDomain(){
        return new Note(
                null,
                this.noteTitle,
                this.noteDescription,
                null
        );
    }

}
