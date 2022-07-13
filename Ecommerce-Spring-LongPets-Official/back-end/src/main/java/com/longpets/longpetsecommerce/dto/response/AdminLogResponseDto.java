package com.longpets.longpetsecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;

public interface AdminLogResponseDto {
    @Value("#{target.log_id}")
    Long getLogId();

    @Value("#{target.log_content}")
    String getLogContent();

    @Value("#{target.log_avatar}")
    String getLogAvatar();
}
