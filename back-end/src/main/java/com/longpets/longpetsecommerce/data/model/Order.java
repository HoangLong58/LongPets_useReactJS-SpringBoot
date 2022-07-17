package com.longpets.longpetsecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_email")
    private String orderEmail;

    @Column(name = "order_phone")
    private String orderPhone;

    @Column(name = "order_address")
    private String orderAddress;

    @Column(name = "order_note")
    private String orderNote;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_total")
    private Long orderTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", foreignKey = @ForeignKey(name = "order_customer_fk1"))
    private Customer customerOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id", referencedColumnName = "ward_id", foreignKey = @ForeignKey(name = "order_ward_fk2"))
    private Ward wardOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", foreignKey = @ForeignKey(name = "order_employee_fk3"))
    private Employee employeeOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_status_id", referencedColumnName = "order_status_id", foreignKey = @ForeignKey(name = "order_orderStatus_fk4"))
    private OrderStatus orderStatusOrder;

    @OneToMany(mappedBy = "orderOrderDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
//    private Set<OrderDetail> orderDetails = new HashSet<>();
    private List<OrderDetail> orderDetails;
}
