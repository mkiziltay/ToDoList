package com.firebase.todolist;

public class MainModel {
    static NotesModel noteModel;

    public MainModel(NotesModel noteModel) {
        this.noteModel = noteModel;
    }

    public MainModel() {
    }

    public NotesModel getNoteModel() {
        return noteModel;
    }

    public void setNoteModel(NotesModel noteModel) {
        this.noteModel = noteModel;
    }
}
