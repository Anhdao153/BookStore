package com.bookstore.bookstore.service.order;

import com.bookstore.bookstore.model.order.Order;

public interface IOrderService {
    Order newOrder(Order order);
}
