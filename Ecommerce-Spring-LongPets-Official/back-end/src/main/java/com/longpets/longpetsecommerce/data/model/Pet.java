package com.longpets.longpetsecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long petId;

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    @Column(name = "pet_name")
    private String petName;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    @Column(name = "pet_gender")
    private String petGender;

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    @Column(name = "pet_age")
    private String petAge;

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    @Column(name = "pet_vaccinated")
    private String petVaccinated;

    public String getPetVaccinated() {
        return petVaccinated;
    }

    public void setPetVaccinated(String petVaccinated) {
        this.petVaccinated = petVaccinated;
    }

    @Column(name = "pet_health_warranty")
    private String petHealthWarranty;

    public String getPetHealthWarranty() {
        return petHealthWarranty;
    }

    public void setPetHealthWarranty(String petHealthWarranty) {
        this.petHealthWarranty = petHealthWarranty;
    }

    @Column(name = "pet_title")
    private String petTitle;

    public String getPetTitle() {
        return petTitle;
    }

    public void setPetTitle(String petTitle) {
        this.petTitle = petTitle;
    }

    @Column(name = "pet_description")
    private String petDescription;

    public String getPetDescription() {
        return petDescription;
    }

    public void setPetDescription(String petDescription) {
        this.petDescription = petDescription;
    }

    @Column(name = "pet_note")
    private String petNote;

    public String getPetNote() {
        return petNote;
    }

    public void setPetNote(String petNote) {
        this.petNote = petNote;
    }

    @Column(name = "pet_quantity")
    private Long petQuantity;

    public Long getPetQuantity() {
        return petQuantity;
    }

    public void setPetQuantity(Long petQuantity) {
        this.petQuantity = petQuantity;
    }

    @Column(name = "pet_price")
    private Long petPrice;

    public Long getPetPrice() {
        return petPrice;
    }

    public void setPetPrice(Long petPrice) {
        this.petPrice = petPrice;
    }

    @Column(name = "pet_price_discount")
    private Long petPriceDiscount;

    public Long getPetPriceDiscount() {
        return petPriceDiscount;
    }

    public void setPetPriceDiscount(Long petPriceDiscount) {
        this.petPriceDiscount = petPriceDiscount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",
            referencedColumnName = "category_id",
            foreignKey = @ForeignKey(name = "pet_category_fk1"))
    private Category categoryPet;

    public Category getCategoryPet() {
        return categoryPet;
    }

    public void setCategoryPet(Category categoryPet) {
        this.categoryPet = categoryPet;
    }

    @OneToMany(mappedBy = "petOrderDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<OrderDetail> orderDetails = new HashSet<>();

    @OneToMany(mappedBy = "petImage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Image> images = new HashSet<>();

    public Pet() {
    }

    public Pet(String petName, String petGender, String petAge, String petVaccinated, String petHealthWarranty,
               String petTitle, String petDescription, String petNote, Long petQuantity, Long petPrice,
               Long petPriceDiscount, Category categoryPet) {
        this.petName = petName;
        this.petGender = petGender;
        this.petAge = petAge;
        this.petVaccinated = petVaccinated;
        this.petHealthWarranty = petHealthWarranty;
        this.petTitle = petTitle;
        this.petDescription = petDescription;
        this.petNote = petNote;
        this.petQuantity = petQuantity;
        this.petPrice = petPrice;
        this.petPriceDiscount = petPriceDiscount;
        this.categoryPet = categoryPet;
    }
}
