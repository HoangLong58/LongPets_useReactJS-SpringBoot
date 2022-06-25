package com.longpets.longpetsecommerce.data.model;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    @Column(name = "order_detail_price")
    private Long orderDetailPrice;

    public Long getOrderDetailPrice() {
        return orderDetailPrice;
    }

    public void setOrderDetailPrice(Long orderDetailPrice) {
        this.orderDetailPrice = orderDetailPrice;
    }

    @Column(name = "order_detail_quantity")
    private Long orderDetailQuantity;

    public Long getOrderDetailQuantity() {
        return orderDetailQuantity;
    }

    public void setOrderDetailQuantity(Long orderDetailQuantity) {
        this.orderDetailQuantity = orderDetailQuantity;
    }

    @Column(name = "order_detail_total")
    private Long orderDetailTotal;

    public Long getOrderDetailTotal() {
        return orderDetailTotal;
    }

    public void setOrderDetailTotal(Long orderDetailTotal) {
        this.orderDetailTotal = orderDetailTotal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id",
            referencedColumnName = "pet_id",
            foreignKey = @ForeignKey(name = "orderDetail_pet_fk1"))
    private Pet petOrderDetail;

    public Pet getPetOrderDetail() {
        return petOrderDetail;
    }

    public void setPetOrderDetail(Pet petOrderDetail) {
        this.petOrderDetail = petOrderDetail;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",
            referencedColumnName = "order_id",
            foreignKey = @ForeignKey(name = "orderDetail_order_fk2"))
    private Order orderOrderDetail;

    public Order getOrderOrderDetail() {
        return orderOrderDetail;
    }

    public void setOrderOrderDetail(Order orderOrderDetail) {
        this.orderOrderDetail = orderOrderDetail;
    }

    public OrderDetail() {
    }

    public OrderDetail(Long orderDetailPrice, Long orderDetailQuantity, Long orderDetailTotal,
                       Pet petOrderDetail, Order orderOrderDetail) {
        this.orderDetailPrice = orderDetailPrice;
        this.orderDetailQuantity = orderDetailQuantity;
        this.orderDetailTotal = orderDetailTotal;
        this.petOrderDetail = petOrderDetail;
        this.orderOrderDetail = orderOrderDetail;
    }
}
