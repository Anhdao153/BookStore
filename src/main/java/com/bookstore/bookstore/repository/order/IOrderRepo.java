package com.bookstore.bookstore.repository.order;

import com.bookstore.bookstore.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends JpaRepository<Order, String> {
    
}
