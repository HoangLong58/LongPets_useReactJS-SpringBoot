package com.longpets.longpetsecommerce.data.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
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
    private Set<District> districts = new HashSet<>();
}