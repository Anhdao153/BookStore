package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.dto.customer.ICustomerDTO;
import com.bookstore.bookstore.model.customer.Customer;
import com.bookstore.bookstore.service.customer.ICustomerService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    ICustomerService iCustomerService;

    @GetMapping("/id")
    public ResponseEntity<Object> findCustomerById(@RequestParam("id") String id) {
        List<ICustomerDTO> customer = iCustomerService.findCustomerById(id);
        if (!iCustomerService.findCustomerById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Customer>> getList(@RequestParam(name = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                                  @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection,
                                                  @RequestParam(defaultValue = "")String id,
                                                  @RequestParam(defaultValue = "")String email,
                                                  @RequestParam(defaultValue = "")int age,
                                                  @RequestParam(defaultValue = "")String address,
                                                  @RequestParam(defaultValue = "")int phoneNumber,
                                                  @RequestParam(defaultValue = "")String orderId) {
        int size = 10;
        Page<Customer> customersPage= iCustomerService.getListCustomer(page,size,id,email,age,address,orderId,phoneNumber,sortField,sortDirection);
       if (customersPage.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(customersPage,HttpStatus.OK);

    }
}
