package com.udacity.jwdnd.course1.cloudstorage.domain;

public class Note {

    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userID;

    public Note(Integer noteId, String noteTitle, String noteDescription, Integer userID) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userID = userID;
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

    public Integer getUserID() {
        return userID;
    }
}
