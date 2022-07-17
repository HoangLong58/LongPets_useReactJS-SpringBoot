package com.longpets.longpetsecommerce.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    @Column(name = "order_detail_price")
    private Long orderDetailPrice;

    @Column(name = "order_detail_quantity")
    private Long orderDetailQuantity;

    @Column(name = "order_detail_total")
    private Long orderDetailTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id",
            referencedColumnName = "pet_id",
            foreignKey = @ForeignKey(name = "orderDetail_pet_fk1"))
    private Pet petOrderDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",
            referencedColumnName = "order_id",
            foreignKey = @ForeignKey(name = "orderDetail_order_fk2"))
    private Order orderOrderDetail;
}
