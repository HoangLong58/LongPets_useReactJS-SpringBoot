package com.longpets.longpetsecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Column(name = "order_name")
    private String orderName;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Column(name = "order_email")
    private String orderEmail;

    public String getOrderEmail() {
        return orderEmail;
    }

    public void setOrderEmail(String orderEmail) {
        this.orderEmail = orderEmail;
    }

    @Column(name = "order_phone")
    private String orderPhone;

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    @Column(name = "order_address")
    private String orderAddress;

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    @Column(name = "order_note")
    private String orderNote;

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    @Column(name = "order_date")
    private Date orderDate;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Column(name = "order_total")
    private Long orderTotal;

    public Long getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Long orderTotal) {
        this.orderTotal = orderTotal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", foreignKey = @ForeignKey(name = "order_customer_fk1"))
    private Customer customerOrder;

    public Customer getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(Customer customerOrder) {
        this.customerOrder = customerOrder;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id", referencedColumnName = "ward_id", foreignKey = @ForeignKey(name = "order_ward_fk2"))
    private Ward wardOrder;

    public Ward getWardOrder() {
        return wardOrder;
    }

    public void setWardOrder(Ward wardOrder) {
        this.wardOrder = wardOrder;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", foreignKey = @ForeignKey(name = "order_employee_fk3"))
    private Employee employeeOrder;

    public Employee getEmployeeOrder() {
        return employeeOrder;
    }

    public void setEmployeeOrder(Employee employeeOrder) {
        this.employeeOrder = employeeOrder;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_status_id", referencedColumnName = "order_status_id", foreignKey = @ForeignKey(name = "order_orderStatus_fk4"))
    private OrderStatus orderStatusOrder;

    public OrderStatus getOrderStatusOrder() {
        return orderStatusOrder;
    }

    public void setOrderStatusOrder(OrderStatus orderStatusOrder) {
        this.orderStatusOrder = orderStatusOrder;
    }

    @OneToMany(mappedBy = "orderOrderDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<OrderDetail> orderDetails = new HashSet<>();

    public Order() {
    }

    public Order(String orderName, String orderEmail, String orderPhone, String orderAddress, String orderNote,
                 Date orderDate, Long orderTotal, Customer customerOrder, Ward wardOrder, Employee employeeOrder,
                 OrderStatus orderStatusOrder) {
        this.orderName = orderName;
        this.orderEmail = orderEmail;
        this.orderPhone = orderPhone;
        this.orderAddress = orderAddress;
        this.orderNote = orderNote;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.customerOrder = customerOrder;
        this.wardOrder = wardOrder;
        this.employeeOrder = employeeOrder;
        this.orderStatusOrder = orderStatusOrder;
    }
}
