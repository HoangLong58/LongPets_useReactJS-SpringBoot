package com.longpets.longpetsecommerce.dto.response;

import com.longpets.longpetsecommerce.data.model.City;

public class DistrictResponeDto {

    private String districtId;

    private String districtName;

    private String districtType;

    private City cityDistrict;

    public DistrictResponeDto() {
    }

    public DistrictResponeDto(String districtId, String districtName, String districtType, City cityDistrict) {
        this.districtId = districtId;
        this.districtName = districtName;
        this.districtType = districtType;
        this.cityDistrict = cityDistrict;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictType() {
        return districtType;
    }

    public void setDistrictType(String districtType) {
        this.districtType = districtType;
    }

    public City getCityDistrict() {
        return cityDistrict;
    }

    public void setCityDistrict(City cityDistrict) {
        this.cityDistrict = cityDistrict;
    }
}
