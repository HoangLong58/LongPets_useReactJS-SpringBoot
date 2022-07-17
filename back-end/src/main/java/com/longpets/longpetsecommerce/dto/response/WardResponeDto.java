package com.longpets.longpetsecommerce.dto.response;

public class WardResponeDto {
    private String wardId;

    private String wardName;

    private String wardType;

    public WardResponeDto() {
    }

    public WardResponeDto(String wardId, String wardName, String wardType) {
        this.wardId = wardId;
        this.wardName = wardName;
        this.wardType = wardType;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getWardType() {
        return wardType;
    }

    public void setWardType(String wardType) {
        this.wardType = wardType;
    }
}
