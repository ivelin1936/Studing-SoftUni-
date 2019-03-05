package com.softuni.exodia.domain.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DocumentScheduleBindingModel {

    @NotNull
    @NotEmpty(message = "Cannot be empty.")
    private String title;

    @NotNull
    @NotEmpty(message = "Cannot be empty.")
    private String content;

    public DocumentScheduleBindingModel() {
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
