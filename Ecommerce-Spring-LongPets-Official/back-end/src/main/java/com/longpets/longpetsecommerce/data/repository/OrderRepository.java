package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Order;
import com.longpets.longpetsecommerce.dto.response.AllOrderDetailOfOrderResponseDto;
import com.longpets.longpetsecommerce.dto.response.AllPetOfOrderDetailResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select o.order_id, o.order_name, o.order_email, o.order_phone, o.order_address, o.order_note, o.order_date, o.order_total, tt.order_status_id, tt.order_status_name, e.employee_id, e.employee_email, e.employee_password, e.employee_name, e.employee_birthday, e.employee_gender, e.employee_phone, e.employee_address, e.employee_avatar, w.ward_id, w.ward_name, w.ward_type, d.district_id, d.district_name, d.district_type, c.city_id, c.city_name, c.city_type from \"order\" o join order_status tt on o.order_status_id = tt.order_status_id join employee e on o.employee_id = e.employee_id join ward w on o.ward_id = w.ward_id join district d on w.district_id = d.district_id join city c on d.city_id = c.city_id where o.order_id = ?",
            nativeQuery = true)
    List<AllOrderDetailOfOrderResponseDto> getAllOrderDetailOfOrder(Long orderId);

    @Query(value = "select p.pet_id, p.pet_name, p.pet_gender, p.pet_age, p.pet_vaccinated, p.pet_health_warranty, p.pet_title, p.pet_description, p.pet_note, p.pet_quantity, p.pet_price, p.pet_price_discount, od.order_detail_id, od.order_detail_price, od.order_detail_quantity, od.order_detail_total from order_detail od join pet p on od.pet_id = p.pet_id WHERE od.order_id = ?;",
            nativeQuery = true)
    List<AllPetOfOrderDetailResponseDto> getAllPetOfOrderDetail(Long orderDetailId);
}
