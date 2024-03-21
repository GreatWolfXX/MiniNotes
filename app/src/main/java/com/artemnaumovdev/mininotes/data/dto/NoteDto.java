package com.artemnaumovdev.mininotes.data.dto;

public class NoteDto {

    private long id;
    private String title, description;

    public NoteDto() {}

    public NoteDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public NoteDto(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
