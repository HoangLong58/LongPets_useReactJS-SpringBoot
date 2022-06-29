package com.longpets.longpetsecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "employee_password")
    private String employeePassword;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_birthday")
    private Date employeeBirthday;

    @Column(name = "employee_gender")
    private String employeeGender;

    @Column(name = "employee_phone")
    private String employeePhone;

    @Column(name = "employee_address")
    private String employeeAddress;

    @Column(name = "employee_avatar")
    private String employeeAvatar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id", referencedColumnName = "ward_id", foreignKey = @ForeignKey(name = "employee_ward_fk2"))
    private Ward wardEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", foreignKey = @ForeignKey(name = "employee_role_fk3"))
    private Role roleEmployee;

    @OneToMany(mappedBy = "employeeOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();
}