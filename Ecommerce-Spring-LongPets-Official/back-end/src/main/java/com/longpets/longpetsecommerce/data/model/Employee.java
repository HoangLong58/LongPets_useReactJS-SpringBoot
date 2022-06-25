package com.longpets.longpetsecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "employee_email")
    private String employeeEmail;

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    @Column(name = "employee_password")
    private String employeePassword;

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    @Column(name = "employee_name")
    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Column(name = "employee_birthday")
    private Date employeeBirthday;

    public Date getEmployeeBirthday() {
        return employeeBirthday;
    }

    public void setEmployeeBirthday(Date employeeBirthday) {
        this.employeeBirthday = employeeBirthday;
    }

    @Column(name = "employee_gender")
    private String employeeGender;

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    @Column(name = "employee_phone")
    private String employeePhone;

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    @Column(name = "employee_address")
    private String employeeAddress;

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    @Column(name = "employee_avatar")
    private String employeeAvatar;

    public String getEmployeeAvatar() {
        return employeeAvatar;
    }

    public void setEmployeeAvatar(String employeeAvatar) {
        this.employeeAvatar = employeeAvatar;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", referencedColumnName = "position_id", foreignKey = @ForeignKey(name = "employee_position_fk1"))
    private Position positionEmployee;

    public Position getPositionEmployee() {
        return positionEmployee;
    }

    public void setPositionEmployee(Position positionEmployee) {
        this.positionEmployee = positionEmployee;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id", referencedColumnName = "ward_id", foreignKey = @ForeignKey(name = "employee_ward_fk2"))
    private Ward wardEmployee;

    public Ward getWardEmployee() {
        return wardEmployee;
    }

    public void setWardEmployee(Ward wardEmployee) {
        this.wardEmployee = wardEmployee;
    }

    @OneToMany(mappedBy = "employeeOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();

    public Employee() {
    }

    public Employee(String employeeEmail, String employeePassword, String employeeName, Date employeeBirthday,
                    String employeeGender, String employeePhone, String employeeAddress, String employeeAvatar,
                    Position positionEmployee, Ward wardEmployee) {
        this.employeeEmail = employeeEmail;
        this.employeePassword = employeePassword;
        this.employeeName = employeeName;
        this.employeeBirthday = employeeBirthday;
        this.employeeGender = employeeGender;
        this.employeePhone = employeePhone;
        this.employeeAddress = employeeAddress;
        this.employeeAvatar = employeeAvatar;
        this.positionEmployee = positionEmployee;
        this.wardEmployee = wardEmployee;
    }
}