package com.bookstore.bookstore.service.orderDetail.Impl;

import com.bookstore.bookstore.dto.oderDetail.OrderDetailDTO;
import com.bookstore.bookstore.model.orderDetail.OrderDetail;
import com.bookstore.bookstore.repository.orderDetail.IOrderDetailRepo;
import com.bookstore.bookstore.service.orderDetail.IOrderDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OderDetailServiceImpl implements IOrderDetailService {

    @Autowired
    IOrderDetailRepo iOrderDetailRepo;

    @Override
    public Page<OrderDetail> findOrderDetailByOrderId(String id, Pageable pageable) {
        return iOrderDetailRepo.findOrderDetailByOrderId(id, pageable);
    }

    @Override
    public OrderDetail saveOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(orderDetailDTO,orderDetail);
        return iOrderDetailRepo.save(orderDetail);
    }

    @Override
    public Optional<OrderDetail> findOrderDetailById(Long id) {
        return iOrderDetailRepo.findById(id);
    }
}
