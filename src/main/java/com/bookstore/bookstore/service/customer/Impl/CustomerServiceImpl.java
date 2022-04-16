package com.bookstore.bookstore.service.customer.Impl;

import com.bookstore.bookstore.dto.customer.CustomerDTO;
import com.bookstore.bookstore.dto.customer.EmailCustomerDTO;
import com.bookstore.bookstore.dto.customer.ICustomer;
import com.bookstore.bookstore.model.customer.Customer;
import com.bookstore.bookstore.repository.customer.ICustomerRepo;
import com.bookstore.bookstore.service.customer.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    ICustomerRepo iCustomerRepo;

    @Override
    public Optional<Customer> findCustomerById(String id) {
        return iCustomerRepo.findById(id);
    }

    @Override
    public Customer saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return iCustomerRepo.save(customer);
    }


    @Override
    public Page<Customer> showListCustomer(String keyWord, Pageable pageable) {
        return iCustomerRepo.showListCustomer("%" + keyWord + "%", pageable);
    }

    @Override
    public void deletedCustomer(String id) {
        iCustomerRepo.deleteCustomerById(id);
    }

    @Override
    public List<ICustomer> getListCustomerMail() {
        return iCustomerRepo.customerSendMail();
    }
}
