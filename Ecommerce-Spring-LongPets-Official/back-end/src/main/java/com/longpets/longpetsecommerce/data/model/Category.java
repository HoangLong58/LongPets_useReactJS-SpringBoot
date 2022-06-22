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
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_title")
    private String categoryTitle;

    @Column(name = "category_image")
    private String categoryImage;

    @OneToMany(mappedBy = "categoryPet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Pet> pets = new HashSet<>();
}
