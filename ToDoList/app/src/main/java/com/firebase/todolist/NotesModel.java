package com.firebase.todolist;

public class NotesModel {
    String notification;
    String  added;
    String  description;
    String  title;
    Boolean  notify;

    public NotesModel(String notification, String added, String description, String title, Boolean notify) {
        this.notification = notification;
        this.added = added;
        this.description = description;
        this.title = title;
        this.notify = notify;
    }

    public NotesModel() {
    }

    public String getNotification() {
        return notification;
    }

    public String getAdded() {
        return added;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getNotify() {
        return notify;
    }

    @Override
    public String toString() {
        return "NotesModel{" +
                "notification='" + notification + '\'' +
                ", added='" + added + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", notify=" + notify +
                '}';
    }
}
