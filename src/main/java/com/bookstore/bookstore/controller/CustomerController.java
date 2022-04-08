package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.dto.customer.CustomerDTO;
import com.bookstore.bookstore.model.customer.Customer;
import com.bookstore.bookstore.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    ICustomerService iCustomerService;

    //lấy tạm cái id ra, sau này làm lịch sử giao dịch thì viết front-end gọi 2 api
    @GetMapping("/detail")
    public ResponseEntity<Object> detailCustomer(@RequestParam("id") String id) {
        Optional<Customer> customer = iCustomerService.findCustomerById(id);
        if (!iCustomerService.findCustomerById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Customer>> getList(@RequestParam(name = "keyWord", defaultValue = "") String keyWord,
                                                  @PageableDefault Pageable pageable) {
        Page<Customer> customers = iCustomerService.showListCustomer(keyWord, pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO, BindingResult bindingResult) {
        new CustomerDTO().validate(customerDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            String mes = "Nhập ngu";
            return new ResponseEntity<>(mes, HttpStatus.BAD_REQUEST);
        }
        Customer customer = iCustomerService.saveCustomer(customerDTO);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    public ResponseEntity<Object> editCustomer(@RequestBody @Valid CustomerDTO customerDTO, BindingResult bindingResult) {
        new CustomerDTO().validate(customerDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            String mes = "Nhập ngu";
            return new ResponseEntity<>(mes, HttpStatus.BAD_REQUEST);
        }
        Customer customer = iCustomerService.saveCustomer(customerDTO);
        return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCustomer(@RequestParam("id") String id) {

        if (!iCustomerService.findCustomerById(id).isPresent()) {
            String mes = "Nhập ngu";
            return new ResponseEntity<>(mes, HttpStatus.NOT_FOUND);
        }
        iCustomerService.deletedCustomer(id);
        String mes = "đã xóa dữ liệu";
        return new ResponseEntity<>(mes, HttpStatus.ACCEPTED);
    }
}
