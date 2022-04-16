package com.bookstore.bookstore.service.customer;

import com.bookstore.bookstore.dto.customer.CustomerDTO;
import com.bookstore.bookstore.dto.customer.EmailCustomerDTO;
import com.bookstore.bookstore.dto.customer.ICustomer;
import com.bookstore.bookstore.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Optional<Customer> findCustomerById(String id);
    Customer saveCustomer(CustomerDTO customerDTO);

    Page<Customer> showListCustomer(String keyWord, Pageable pageable);

    void deletedCustomer(String id);

    List<ICustomer> getListCustomerMail();
}
