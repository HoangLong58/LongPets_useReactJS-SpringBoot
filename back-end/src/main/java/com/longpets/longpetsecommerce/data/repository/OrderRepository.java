package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Order;
import com.longpets.longpetsecommerce.dto.response.*;
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
    void addOrder(Long customerId, String wardId, String orderName, String orderEmail, String orderPhone, String orderAddress, String orderNote, Date orderDate, Long orderTotal);

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
//  =====

    @Query(value = "select o.order_id, o.customer_id, o.order_name, o.order_email, o.order_phone, o.order_address, o.order_note, o.order_date, o.order_total, o.order_status_id, os.order_status_name, w.ward_id, w.ward_name, w.ward_type, d.district_id, d.district_name, d.district_type, c.city_id, c.city_name, c.city_type from \"order\" o join ward w on o.ward_id = w.ward_id join district d on w.district_id = d.district_id join city c on d.city_id = c.city_id join order_status os on o.order_status_id = os.order_status_id where o.customer_id = ? order by o.order_date desc;",
            nativeQuery = true)
    List<OrderByCustomerIdResponseDto> getOrderByCustomerId(Long customerId);


    @Query(value = "select min(dm.category_name) as category_name, sum(cd.order_detail_total) as money_total from \"order\" d join order_detail cd on d.order_id = cd.order_id join pet t on cd.pet_id = t.pet_id join category dm on t.category_id = dm.category_id join customer n on d.customer_id = n.customer_id where d.order_status_id = 2 AND dm.category_name = 'Chó' AND extract(DAY from date (d.order_date)) = ? AND extract(MONTH from date (d.order_date)) = ? AND extract(YEAR from date (d.order_date)) = ?;",
            nativeQuery = true)
    CategoryProfitResponseDto getDogProfit(Long day, Long month, Long year);
    @Query(value = "select min(dm.category_name) as category_name, sum(cd.order_detail_total) as money_total from \"order\" d join order_detail cd on d.order_id = cd.order_id join pet t on cd.pet_id = t.pet_id join category dm on t.category_id = dm.category_id join customer n on d.customer_id = n.customer_id where d.order_status_id = 2 AND dm.category_name = 'Chó' AND extract(YEAR from date (d.order_date)) = '2022';",
            nativeQuery = true)
    CategoryProfitResponseDto getDogProfitYear();

    @Query(value = "select min(dm.category_name) as category_name, sum(cd.order_detail_total) as money_total from \"order\" d join order_detail cd on d.order_id = cd.order_id join pet t on cd.pet_id = t.pet_id join category dm on t.category_id = dm.category_id join customer n on d.customer_id = n.customer_id where d.order_status_id = 2 AND dm.category_name = 'Mèo' AND extract(DAY from date (d.order_date)) = ? AND extract(MONTH from date (d.order_date)) = ? AND extract(YEAR from date (d.order_date)) = ?;",
            nativeQuery = true)
    CategoryProfitResponseDto getCatProfit(Long day, Long month, Long year);
    @Query(value = "select min(dm.category_name) as category_name, sum(cd.order_detail_total) as money_total from \"order\" d join order_detail cd on d.order_id = cd.order_id join pet t on cd.pet_id = t.pet_id join category dm on t.category_id = dm.category_id join customer n on d.customer_id = n.customer_id where d.order_status_id = 2 AND dm.category_name = 'Mèo' AND extract(YEAR from date (d.order_date)) = '2022';",
            nativeQuery = true)
    CategoryProfitResponseDto getCatProfitYear();

    @Query(value = "select min(dm.category_name) as category_name, sum(cd.order_detail_total) as money_total from \"order\" d join order_detail cd on d.order_id = cd.order_id join pet t on cd.pet_id = t.pet_id join category dm on t.category_id = dm.category_id join customer n on d.customer_id = n.customer_id where d.order_status_id = 2 AND dm.category_name != 'Chó' AND dm.category_name != 'Mèo' AND extract(DAY from date (d.order_date)) = ? AND extract(MONTH from date (d.order_date)) = ? AND extract(YEAR from date (d.order_date)) = ?;",
            nativeQuery = true)
    CategoryProfitResponseDto getAnotherProfit(Long day, Long month, Long year);
    @Query(value = "select min(dm.category_name) as category_name, sum(cd.order_detail_total) as money_total from \"order\" d join order_detail cd on d.order_id = cd.order_id join pet t on cd.pet_id = t.pet_id join category dm on t.category_id = dm.category_id join customer n on d.customer_id = n.customer_id where d.order_status_id = 2 AND dm.category_name != 'Chó' AND dm.category_name != 'Mèo' AND extract(YEAR from date (d.order_date)) = '2022';",
            nativeQuery = true)
    CategoryProfitResponseDto getAnotherProfitYear();

    @Query(value = "select sum(cd.order_detail_total) as money_total from \"order\" d join order_detail cd on d.order_id = cd.order_id join pet t on cd.pet_id = t.pet_id join category dm on t.category_id = dm.category_id join customer n on d.customer_id = n.customer_id where d.order_status_id = 2 AND extract(DAY from date (d.order_date)) = ? AND extract(MONTH from date (d.order_date)) = ? AND extract(YEAR from date (d.order_date)) = ?;",
            nativeQuery = true)
    MoneyTotalResponseDto getMoneyTotal(Long day, Long month, Long year);
    @Query(value = "select sum(cd.order_detail_total) as money_total from \"order\" d join order_detail cd on d.order_id = cd.order_id join pet t on cd.pet_id = t.pet_id join category dm on t.category_id = dm.category_id join customer n on d.customer_id = n.customer_id where d.order_status_id = 2 AND extract(YEAR from date (d.order_date)) = '2022';",
            nativeQuery = true)
    MoneyTotalResponseDto getMoneyTotalYear();

    @Query(value = "select dm.category_name, sum(case extract(MONTH from date (d.order_date)) when 1 then ct.order_detail_total else 0 END) as month1, sum(case extract(MONTH from date (d.order_date)) when 2 then ct.order_detail_total else 0 END) as month2, sum(case extract(MONTH from date (d.order_date)) when 3 then ct.order_detail_total else 0 END) as month3, sum(case extract(MONTH from date (d.order_date)) when 4 then ct.order_detail_total else 0 END) as month4, sum(case extract(MONTH from date (d.order_date)) when 5 then ct.order_detail_total else 0 END) as month5, sum(case extract(MONTH from date (d.order_date)) when 6 then ct.order_detail_total else 0 END) as month6, sum(case extract(MONTH from date (d.order_date)) when 7 then ct.order_detail_total else 0 END) as month7, sum(case extract(MONTH from date (d.order_date)) when 8 then ct.order_detail_total else 0 END) as month8, sum(case extract(MONTH from date (d.order_date)) when 9 then ct.order_detail_total else 0 END) as month9, sum(case extract(MONTH from date (d.order_date)) when 10 then ct.order_detail_total else 0 END) as month10, sum(case extract(MONTH from date (d.order_date)) when 11 then ct.order_detail_total else 0 END) as month11, sum(case extract(MONTH from date (d.order_date)) when 12 then ct.order_detail_total else 0 END) as month12, sum(ct.order_detail_total) as total_year from \"order\" d join order_detail ct on d.order_id = ct.order_id join pet t on ct.pet_id = t.pet_id join category dm on t.category_id = dm.category_id WHERE extract(YEAR from date (d.order_date)) = 2022 and d.order_status_id = 2 GROUP BY dm.category_id;",
            nativeQuery = true)
    List<MoneyTotal12MonthResponseDto> getMoneyTotal12Month();

    @Query(value = "select count(order_id) as order_quantity_today from \"order\" where extract(DAY from date (order_date)) = extract(DAY from date (now())) and extract(MONTH from date (order_date)) = extract(MONTH from date (now())) and extract(YEAR from date (order_date)) = extract(YEAR from date (now()));",
            nativeQuery = true)
    OrderTodayResponseDto getOrderToday();

    @Query(value = "select sum(order_total) as money_total_today from \"order\" where extract(DAY from date (order_date)) = extract(DAY from date (now())) and extract(MONTH from date (order_date)) = extract(MONTH from date (now())) and extract(YEAR from date (order_date)) = extract(YEAR from date (now())) and order_status_id = 2;",
            nativeQuery = true)
    MoneyTotalTodayResponseDto getMoneyTotalToday();

    @Query(value = "select count(order_id) as order_quantity_need_allow from \"order\" where extract(DAY from date (order_date)) = extract(DAY from date (now())) and extract(MONTH from date (order_date)) = extract(MONTH from date (now())) and extract(YEAR from date (order_date)) = extract(YEAR from date (now())) and order_status_id = 1",
            nativeQuery = true)
    OrderQuantityNeedAllowResponseDto getOrderQuantityNeedAllow();

    @Query(value = "select o.order_id, o.order_name, o.order_email, o.order_phone, o.order_address, o.order_note, o.order_date, o.order_total, o.order_status_id, os.order_status_name, w.ward_id, w.ward_name, w.ward_type, d.district_id, d.district_name, d.district_type, c.city_id, c.city_name, c.city_type from \"order\" o join ward w on o.ward_id = w.ward_id join district d on w.district_id = d.district_id join city c on d.city_id = c.city_id join order_status os on o.order_status_id = os.order_status_id order by o.order_id desc;",
            nativeQuery = true)
    List<AllOrderResponseDto> getAllOrder();

    @Query(value = "select o.order_id, o.order_name, o.order_email, o.order_phone, o.order_address, o.order_note, o.order_date, o.order_total, o.order_status_id, os.order_status_name, w.ward_id, w.ward_name, w.ward_type, d.district_id, d.district_name, d.district_type, c.city_id, c.city_name, c.city_type from \"order\" o join ward w on o.ward_id = w.ward_id join district d on w.district_id = d.district_id join city c on d.city_id = c.city_id join order_status os on o.order_status_id = os.order_status_id where o.order_id = ? order by o.order_id desc;",
            nativeQuery = true)
    List<AllOrderResponseDto> getAllOrderByOrderId(Long orderId);

    @Query(value = "select count(order_id) as order_quantity from \"order\";",
            nativeQuery = true)
    OrderQuantityResponseDto getOrderQuantity();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update \"order\" set order_status_id = 2, employee_id = ? where order_id = ?;",
            nativeQuery = true)
    void acceptOrder(Long employeeId ,Long orderId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into admin_log (log_content, log_avatar) values (concat(?,' đã duyệt đơn đặt mua có mã ', ?), ?);",
            nativeQuery = true)
    void addLog(String employeeName, Long orderId, String employeeAvatar);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update \"order\" set order_status_id = 4, employee_id = ? where order_id = ?;",
            nativeQuery = true)
    void denyOrder(Long employeeId ,Long orderId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into admin_log (log_content, log_avatar) values (concat(?,' đã từ chối đơn đặt mua có mã ', ?), ?);",
            nativeQuery = true)
    void addLogDeny(String employeeName, Long orderId, String employeeAvatar);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update pet set pet_quantity = pet_quantity + ? where pet_id = ?;",
            nativeQuery = true)
    void updatePetQuantityAfterDeny(Long petQuantityDeny ,Long petId);

}
