package com.longpets.longpetsecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
//    @JsonBackReference
    private District districtWard;

    @OneToMany(mappedBy = "wardEmployee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
//    private Set<Employee> employees = new HashSet<>();
    private List<Employee> employees;

    @OneToMany(mappedBy = "wardOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
//    private Set<Order> orders = new HashSet<>();
    private List<Order> orders;

    @OneToMany(mappedBy = "wardCustomer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
//    private Set<Customer> customers = new HashSet<>();
    private List<Customer> customers;

}
