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
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "pet_name")
    private String petName;

    @Column(name = "pet_gender")
    private String petGender;

    @Column(name = "pet_age")
    private String petAge;

    @Column(name = "pet_vaccinated")
    private String petVaccinated;

    @Column(name = "pet_health_warranty")
    private String petHealthWarranty;

    @Column(name = "pet_title")
    private String petTitle;

    @Column(name = "pet_description")
    private String petDescription;

    @Column(name = "pet_note")
    private String petNote;

    @Column(name = "pet_quantity")
    private Long petQuantity;

    @Column(name = "pet_price")
    private Long petPrice;

    @Column(name = "pet_price_discount")
    private Long petPriceDiscount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",
            referencedColumnName = "category_id",
            foreignKey = @ForeignKey(name = "pet_category_fk1"))
    private Category categoryPet;

    @OneToMany(mappedBy = "petOrderDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<OrderDetail> orderDetails = new HashSet<>();

    @OneToMany(mappedBy = "petImage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Image> images = new HashSet<>();
}
