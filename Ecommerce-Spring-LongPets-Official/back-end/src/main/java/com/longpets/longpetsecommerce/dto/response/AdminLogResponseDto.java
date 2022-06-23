package com.longpets.longpetsecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminLogResponseDto {
    private Long logId;

    private String logContent;

    @JsonProperty("logAvatarAfterCustom")
    private String logAvatar;
}
