package com.longpets.longpetsecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminLogResponseDto {
    private Long logId;

    private String logContent;

    @JsonProperty("logAvatarAfterCustom")
    private String logAvatar;

    public AdminLogResponseDto() {
    }

    public AdminLogResponseDto(Long logId, String logContent, String logAvatar) {
        this.logId = logId;
        this.logContent = logContent;
        this.logAvatar = logAvatar;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getLogAvatar() {
        return logAvatar;
    }

    public void setLogAvatar(String logAvatar) {
        this.logAvatar = logAvatar;
    }
}
