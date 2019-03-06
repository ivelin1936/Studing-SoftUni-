package com.softuni.exodia.domain.models.service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DocumentServiceModel {

    private String id;

    @NotNull
    @NotEmpty(message = "Cannot be empty.")
    private String title;

    @NotNull
    @NotEmpty(message = "Cannot be empty.")
    private String content;

    public DocumentServiceModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
