package com.bookstore.bookstore.service.customer;

import com.bookstore.bookstore.dto.customer.CustomerDTO;
import com.bookstore.bookstore.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICustomerService {
    Optional<Customer> findCustomerById(String id);
    Customer saveCustomer(CustomerDTO customerDTO);
    Page<Customer> getListCustomer(int page, int size,String id, String email, int age, String address, String orderId, int phoneNumber,
                                   String sortField, String sortDirection);
}
