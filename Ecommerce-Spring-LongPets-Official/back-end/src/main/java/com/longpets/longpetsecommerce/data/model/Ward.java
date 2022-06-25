package com.longpets.longpetsecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ward")
public class Ward {

    @Id
    @Column(name = "ward_id")
    private String wardId;

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    @Column(name = "ward_name")
    private String wardName;

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    @Column(name = "ward_type")
    private String wardType;

    public String getWardType() {
        return wardType;
    }

    public void setWardType(String wardType) {
        this.wardType = wardType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id",
            referencedColumnName = "district_id",
            foreignKey = @ForeignKey(name = "ward_district_fk1"))
//    @JsonBackReference
    private District districtWard;

    public District getDistrictWard() {
        return districtWard;
    }

    public void setDistrictWard(District districtWard) {
        this.districtWard = districtWard;
    }

    @OneToMany(mappedBy = "wardEmployee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "wardOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "wardCustomer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Customer> customers = new HashSet<>();

    public Ward() {
    }

    public Ward(String wardId, String wardName, String wardType, District districtWard) {
        this.wardId = wardId;
        this.wardName = wardName;
        this.wardType = wardType;
        this.districtWard = districtWard;
    }
}
