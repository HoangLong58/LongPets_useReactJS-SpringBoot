package com.longpets.longpetsecommerce.data.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "city")
public class City {

    @Id
    @Column(name = "city_id")
    private String cityId;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Column(name = "city_name")
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Column(name = "city_type")
    private String cityType;

    public String getCityType() {
        return cityType;
    }

    public void setCityType(String cityType) {
        this.cityType = cityType;
    }

    @OneToMany(mappedBy = "cityDistrict", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<District> districts = new HashSet<>();

    public City() {
    }

    public City(String cityId, String cityName, String cityType) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityType = cityType;
    }
}