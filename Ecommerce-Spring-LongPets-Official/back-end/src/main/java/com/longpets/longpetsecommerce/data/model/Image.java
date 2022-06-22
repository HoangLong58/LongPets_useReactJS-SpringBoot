package com.longpets.longpetsecommerce.data.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
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

