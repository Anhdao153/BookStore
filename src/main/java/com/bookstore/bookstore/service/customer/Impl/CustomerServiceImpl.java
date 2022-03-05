package com.bookstore.bookstore.service.customer.Impl;

import com.bookstore.bookstore.dto.customer.CustomerDTO;
import com.bookstore.bookstore.model.customer.Customer;
import com.bookstore.bookstore.repository.customer.ICustomerRepo;
import com.bookstore.bookstore.service.customer.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public Page<Customer> getListCustomer(int page, int size, String id, String email, int age, String address, String orderId, int phoneNumber,String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        return iCustomerRepo.getListCustomer(id,email,age,address,phoneNumber,orderId,pageable);
    }
}
