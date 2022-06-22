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
@Table(name = "ward")
public class Ward {

    @Id
    @Column(name = "ward_id")
    private String wardId;

    @Column(name = "ward_name")
    private String wardName;

    @Column(name = "ward_type")
    private String wardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id",
                referencedColumnName = "district_id",
                foreignKey = @ForeignKey(name = "ward_district_fk1"))
    private District districtWard;

    @OneToMany(mappedBy = "wardEmployee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "wardOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "wardCustomer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Customer> customers = new HashSet<>();
}
