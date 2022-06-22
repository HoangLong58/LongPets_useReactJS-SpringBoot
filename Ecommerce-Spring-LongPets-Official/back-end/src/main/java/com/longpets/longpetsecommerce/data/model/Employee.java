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
    @JoinColumn(name = "position_id",
            referencedColumnName = "position_id",
            foreignKey = @ForeignKey(name = "employee_position_fk1"))
    private Position positionEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id",
            referencedColumnName = "ward_id",
            foreignKey = @ForeignKey(name = "employee_ward_fk2"))
    private Ward wardEmployee;

    @OneToMany(mappedBy = "employeeOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();
}