package com.longpets.longpetsecommerce.dto.request;

import javax.validation.constraints.NotBlank;

public class AdminLogUpdateDto {
    @NotBlank(message = "Image can't be empty")
    private String logAvatar;

    @NotBlank(message = "Content can't be empty")
    private String logContent;

    public AdminLogUpdateDto() {
    }

    public AdminLogUpdateDto(@NotBlank(message = "Image can't be empty") String logAvatar,
            @NotBlank(message = "Content can't be empty") String logContent) {
        this.logAvatar = logAvatar;
        this.logContent = logContent;
    }

    public String getLogAvatar() {
        return logAvatar;
    }

    public void setLogAvatar(String logAvatar) {
        this.logAvatar = logAvatar;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }
}
