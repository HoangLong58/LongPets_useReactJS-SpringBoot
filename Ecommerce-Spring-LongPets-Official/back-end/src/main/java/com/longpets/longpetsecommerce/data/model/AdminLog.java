package com.longpets.longpetsecommerce.data.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "admin_log")
public class AdminLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    @Column(name = "log_content")
    private String logContent;

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    @Column(name = "log_avatar")
    private String logAvatar;

    public AdminLog() {
    }

    public AdminLog(Long logId, String logContent, String logAvatar) {
        this.logId = logId;
        this.logContent = logContent;
        this.logAvatar = logAvatar;
    }

    public String getLogAvatar() {
        return logAvatar;
    }

    public void setLogAvatar(String logAvatar) {
        this.logAvatar = logAvatar;
    }
}

