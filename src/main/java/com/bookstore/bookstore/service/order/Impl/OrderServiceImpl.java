package com.bookstore.bookstore.service.order.Impl;

import com.bookstore.bookstore.model.order.Order;
import com.bookstore.bookstore.repository.order.IOrderRepo;
import com.bookstore.bookstore.repository.orderDetail.IOrderDetailRepo;
import com.bookstore.bookstore.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    IOrderRepo iOrderRepo;
    @Override
    public Order newOrder(Order order) {
        return iOrderRepo.save(order);
    }
}
