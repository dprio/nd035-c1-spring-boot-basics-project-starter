package com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request;

import com.udacity.jwdnd.course1.cloudstorage.domain.Note;

public class NoteRequest {

    private Integer noteId;
    private String noteTitle;
    private String noteDescription;


    public NoteRequest(Integer noteId, String noteTitle, String noteDescription) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    public Note toNoteDomain(){
        return new Note(
                this.noteId,
                this.noteTitle,
                this.noteDescription,
                null
        );
    }

    public Integer getNoteId() {
        return noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }
}
