package com.longpets.longpetsecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
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

    @ManyToMany
    @JoinTable(
            name = "customer_role",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new ArrayList<>();
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role_id", referencedColumnName = "role_id", foreignKey = @ForeignKey(name = "customer_role_fk2"))
//    private Role roleCustomer;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
//    private Set<Order> orders = new HashSet<>();
    private List<Order> orders;
}