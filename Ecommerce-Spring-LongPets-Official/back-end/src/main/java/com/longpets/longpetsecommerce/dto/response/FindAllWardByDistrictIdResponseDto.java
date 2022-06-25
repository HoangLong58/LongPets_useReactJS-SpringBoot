package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface FindAllWardByDistrictIdResponseDto {
//    @Value("#{target.districtId}")
    String getDistrictId();

//    @Value("#{target.districtName}")
    String getDistrictName();

    @Value("#{target.wardId}")
    String getWardId();

    @Value("#{target.wardName}")
    String getWardName();

    @Value("#{target.wardType}")
    String getWardType();

//    public FindAllWardByDistrictIdResponseDto() {
//    }
//
//    public FindAllWardByDistrictIdResponseDto(String districtId, String districtName, String wardId, String wardName, String wardType) {
//        this.districtId = districtId;
//        this.districtName = districtName;
//        this.wardId = wardId;
//        this.wardName = wardName;
//        this.wardType = wardType;
//    }
//
//    public String getDistrictId() {
//        return districtId;
//    }
//
//    public void setDistrictId(String districtId) {
//        this.districtId = districtId;
//    }
//
//    public String getDistrictName() {
//        return districtName;
//    }
//
//    public void setDistrictName(String districtName) {
//        this.districtName = districtName;
//    }
//
//    public String getWardId() {
//        return wardId;
//    }
//
//    public void setWardId(String wardId) {
//        this.wardId = wardId;
//    }
//
//    public String getWardName() {
//        return wardName;
//    }
//
//    public void setWardName(String wardName) {
//        this.wardName = wardName;
//    }
//
//    public String getWardType() {
//        return wardType;
//    }
//
//    public void setWardType(String wardType) {
//        this.wardType = wardType;
//    }
}
