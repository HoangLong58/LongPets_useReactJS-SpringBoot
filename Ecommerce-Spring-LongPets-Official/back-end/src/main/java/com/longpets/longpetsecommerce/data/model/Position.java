package com.longpets.longpetsecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long positionId;

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    @Column(name = "position_name")
    private String positionName;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Column(name = "position_salary_factor")
    private Long positionSalaryFactor;

    public Long getPositionSalaryFactor() {
        return positionSalaryFactor;
    }

    public void setPositionSalaryFactor(Long positionSalaryFactor) {
        this.positionSalaryFactor = positionSalaryFactor;
    }

    @OneToMany(mappedBy = "positionEmployee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();

    public Position() {
    }

    public Position(String positionName, Long positionSalaryFactor) {
        this.positionName = positionName;
        this.positionSalaryFactor = positionSalaryFactor;
    }
}
