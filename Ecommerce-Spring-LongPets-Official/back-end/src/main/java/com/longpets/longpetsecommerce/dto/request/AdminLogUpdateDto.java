package com.longpets.longpetsecommerce.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminLogUpdateDto {
    @NotBlank(message = "Nội dung log không được trống")
    private String logContent;

    @NotBlank(message = "Hình đại diện không được trống")
    private String logAvatar;
}
