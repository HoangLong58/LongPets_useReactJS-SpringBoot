package com.longpets.longpetsecommerce.dto.request;

import javax.validation.constraints.NotBlank;

public class AdminLogUpdateDto {
    @NotBlank(message = "Hình đại diện không được trống")
    private String logAvatar;

    @NotBlank(message = "Nội dung log không được trống")
    private String logContent;

    public AdminLogUpdateDto() {
    }

    public AdminLogUpdateDto(@NotBlank(message = "Hình đại diện không được trống") String logAvatar,
            @NotBlank(message = "Nội dung log không được trống") String logContent) {
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
