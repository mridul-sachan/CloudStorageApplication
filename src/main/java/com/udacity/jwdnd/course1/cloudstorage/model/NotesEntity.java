package com.udacity.jwdnd.course1.cloudstorage.model;

public class NotesEntity {

    private Integer noteId;
    private Integer userId;
    private String noteDesription;
    private String noteTitle;

    public NotesEntity(Integer noteId, Integer userId, String noteDesription, String noteTitle) {
        this.noteId = noteId;
        this.userId = userId;
        this.noteDesription = noteDesription;
        this.noteTitle = noteTitle;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNoteDesription() {
        return noteDesription;
    }

    public void setNoteDesription(String noteDesription) {
        this.noteDesription = noteDesription;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }
}
