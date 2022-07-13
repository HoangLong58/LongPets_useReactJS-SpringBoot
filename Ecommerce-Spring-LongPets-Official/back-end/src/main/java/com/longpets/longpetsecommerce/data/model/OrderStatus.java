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
@Table(name = "order_status")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_status_id")
    private Long orderStatusId;

    @Column(name = "order_status_name")
    private String orderStatusName;

    @OneToMany(mappedBy = "orderStatusOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
//    private Set<Order> orders = new HashSet<>();
    private List<Order> orders;
}
