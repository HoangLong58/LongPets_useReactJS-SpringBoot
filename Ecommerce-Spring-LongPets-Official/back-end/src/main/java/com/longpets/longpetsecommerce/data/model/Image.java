package com.longpets.longpetsecommerce.data.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    @Column(name = "image_content")
    private String imageContent;

    public String getImageContent() {
        return imageContent;
    }

    public void setImageContent(String imageContent) {
        this.imageContent = imageContent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id",
            referencedColumnName = "pet_id",
            foreignKey = @ForeignKey(name = "image_pet_fk1"))
    private Pet petImage;

    public Image(Long imageId, String imageContent, Pet petImage) {
        this.imageId = imageId;
        this.imageContent = imageContent;
        this.petImage = petImage;
    }

    public Image() {
    }

    public Pet getPetImage() {
        return petImage;
    }

    public void setPetImage(Pet petImage) {
        this.petImage = petImage;
    }
}

