package com.longpets.longpetsecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "district")
public class District {

    @Id
    @Column(name = "district_id")
    private String districtId;

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    @Column(name = "district_name")
    private String districtName;

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Column(name = "district_type")
    private String districtType;

    public String getDistrictType() {
        return districtType;
    }

    public void setDistrictType(String districtType) {
        this.districtType = districtType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id",
            referencedColumnName = "city_id",
            foreignKey = @ForeignKey(name = "district_city_fk1"))
    private City cityDistrict;

    public City getCityDistrict() {
        return cityDistrict;
    }

    public void setCityDistrict(City cityDistrict) {
        this.cityDistrict = cityDistrict;
    }

    @OneToMany(mappedBy = "districtWard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
//    @JsonManagedReference
    private Set<Ward> wards = new HashSet<>();

    public District() {
    }

    public District(String districtId, String districtName, String districtType, City cityDistrict) {
        this.districtId = districtId;
        this.districtName = districtName;
        this.districtType = districtType;
        this.cityDistrict = cityDistrict;
    }
}
