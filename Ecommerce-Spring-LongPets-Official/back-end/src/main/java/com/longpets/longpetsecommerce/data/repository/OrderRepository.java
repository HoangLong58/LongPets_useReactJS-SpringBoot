package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Order;
import com.longpets.longpetsecommerce.dto.response.AllDetailOrderByOrderIdResponseDto;
import com.longpets.longpetsecommerce.dto.response.AllOrderDetailOfOrderResponseDto;
import com.longpets.longpetsecommerce.dto.response.AllPetOfOrderDetailResponseDto;
import com.longpets.longpetsecommerce.dto.response.OrderByOrderDateResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select o.order_id, o.order_name, o.order_email, o.order_phone, o.order_address, o.order_note, o.order_date, o.order_total, tt.order_status_id, tt.order_status_name, e.employee_id, e.employee_email, e.employee_password, e.employee_name, e.employee_birthday, e.employee_gender, e.employee_phone, e.employee_address, e.employee_avatar, w.ward_id, w.ward_name, w.ward_type, d.district_id, d.district_name, d.district_type, c.city_id, c.city_name, c.city_type from \"order\" o join order_status tt on o.order_status_id = tt.order_status_id join employee e on o.employee_id = e.employee_id join ward w on o.ward_id = w.ward_id join district d on w.district_id = d.district_id join city c on d.city_id = c.city_id where o.order_id = ?",
            nativeQuery = true)
    List<AllOrderDetailOfOrderResponseDto> getAllOrderDetailOfOrder(Long orderId);

    @Query(value = "select p.pet_id, p.pet_name, p.pet_gender, p.pet_age, p.pet_vaccinated, p.pet_health_warranty, p.pet_title, p.pet_description, p.pet_note, p.pet_quantity, p.pet_price, p.pet_price_discount, od.order_detail_id, od.order_detail_price, od.order_detail_quantity, od.order_detail_total from order_detail od join pet p on od.pet_id = p.pet_id WHERE od.order_id = ?;",
            nativeQuery = true)
    List<AllPetOfOrderDetailResponseDto> getAllPetOfOrderDetail(Long orderDetailId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update \"order\" set employee_id = 0, order_status_id = 4 where order_id = ?;",
            nativeQuery = true)
    void updateUserCancelOrder(Long orderId);

    @Query(value = "select order_detail_id, order_detail_price, order_detail_quantity, order_detail_total, pet_id, order_id from order_detail where order_id = ?",
            nativeQuery = true)
    List<AllDetailOrderByOrderIdResponseDto> findAllDetailOrderByOrderId(Long orderId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update pet set pet_quantity = pet_quantity + ? where pet_id = ?;",
            nativeQuery = true)
    void updatePetQuantityInOrderDetail(Long order_detail_quantity, Long pet_id);

//    ===== Feature: Order

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into \"order\" (customer_id, ward_id, employee_id, order_name, order_email, order_phone, order_address, order_note, order_date, order_status_id, order_total) values (?, ?, 0, ?, ?, ?, ?, ?, ?, 1, ?);",
            nativeQuery = true)
    void addOrder(Long customerId, String wardId, String orderName, String orderEmail, String orderPhone, String orderAddress, String orderNote, Date orderDate,  Long orderTotal);

    @Query(value = "select order_id, order_name, order_email, order_phone, order_address, order_note, order_date, order_total, customer_id, ward_id, employee_id, order_status_id from \"order\" where order_date = ? AND customer_id = ? AND order_phone = ?;",
            nativeQuery = true)
    List<OrderByOrderDateResponseDto> getOrderByOrderDate(Date orderDate, Long customerId, String orderPhone);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into order_detail (pet_id, order_id, order_detail_price, order_detail_quantity, order_detail_total) values ( ?, ?, ?, ?, ?)",
            nativeQuery = true)
    void addOrderDetail(Long petId, Long orderId, Long orderDetailPrice, Long orderDetailQuantity, Long orderDetailTotal);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update pet set pet_quantity = pet_quantity - ? where pet_id = ?;",
            nativeQuery = true)
    void updatePetQuantityAfterOrder(Long petQuantityBuy ,Long petId);
}
