package com.bookstore.bookstore.repository.orderDetail;

import com.bookstore.bookstore.model.orderDetail.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderDetailRepo extends JpaRepository<OrderDetail, Long> {

//    @Query(value = "SELECT * FROM orderdetails where order_id = ?1",nativeQuery = true)
//    Page<OrderDetail> findBills(String oderId);

    @Query(value = "SELECT * FROM orderdetails where order_id = ?1", nativeQuery = true)
    Page<OrderDetail> findOrderDetailByOrderId(String orderId, Pageable pageable);
}
