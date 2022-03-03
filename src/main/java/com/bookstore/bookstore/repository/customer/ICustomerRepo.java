package com.bookstore.bookstore.repository.customer;

import com.bookstore.bookstore.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepo extends JpaRepository<Customer,String> {
}
