package com.bookstore.bookstore.repository.orderDetail;

import com.bookstore.bookstore.model.orderDetail.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetail extends JpaRepository<OrderDetail, Long> {
}
