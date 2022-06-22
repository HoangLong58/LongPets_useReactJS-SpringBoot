package com.longpets.longpetsecommerce.data.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_password")
    private String customerPassword;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_birthday")
    private Date customerBirthday;

    @Column(name = "customer_gender")
    private String customerGender;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_avatar")
    private String customerAvatar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id",
            referencedColumnName = "ward_id",
            foreignKey = @ForeignKey(name = "customer_ward_fk1"))
    private Ward wardCustomer;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();
}