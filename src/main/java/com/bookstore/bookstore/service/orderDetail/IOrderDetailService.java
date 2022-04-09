package com.bookstore.bookstore.service.orderDetail;

import com.bookstore.bookstore.dto.oderDetail.ListOrder;
import com.bookstore.bookstore.dto.oderDetail.OrderDetailDTO;
import com.bookstore.bookstore.model.orderDetail.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IOrderDetailService {
    Page<OrderDetail> findOrderDetailByOrderId(String id, Pageable pageable);

    String saveOrderDetail(ListOrder orderDetailDTO);

    Optional<OrderDetail> findOrderDetailById(Long id);

   Page<OrderDetail> findOrderDetailByCustomerId(String id, Pageable pageable);

    Page<OrderDetail> findAllOrderDetail(Pageable pageable);
}
