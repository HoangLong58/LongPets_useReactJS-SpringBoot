package com.longpets.longpetsecommerce.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "image_content")
    private String imageContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id",
            referencedColumnName = "pet_id",
            foreignKey = @ForeignKey(name = "image_pet_fk1"))
    private Pet petImage;
}

