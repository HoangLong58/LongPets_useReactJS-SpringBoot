package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

@Data
public class AcceptOrderRequestDto {
    private Long orderId;
    private Long employeeId;
    private String employeeName;
    private String employeeAvatar;
}
