package com.longpets.longpetsecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "district")
public class District {

    @Id
    @Column(name = "district_id")
    private String districtId;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "district_type")
    private String districtType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id",
            referencedColumnName = "city_id",
            foreignKey = @ForeignKey(name = "district_city_fk1"))
    private City cityDistrict;

    @OneToMany(mappedBy = "districtWard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
//    @JsonManagedReference
    private Set<Ward> wards = new HashSet<>();
}
