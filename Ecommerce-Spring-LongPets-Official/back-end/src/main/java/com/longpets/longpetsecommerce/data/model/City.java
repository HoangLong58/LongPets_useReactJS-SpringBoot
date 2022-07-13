package com.longpets.longpetsecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city")
public class City {

    @Id
    @Column(name = "city_id")
    private String cityId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "city_type")
    private String cityType;

    @OneToMany(mappedBy = "cityDistrict", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
//    private Set<District> districts = new HashSet<>();
    private List<District> districts;

}