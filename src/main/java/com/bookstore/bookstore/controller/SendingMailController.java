package com.bookstore.bookstore.controller;


import com.bookstore.bookstore.dto.customer.CustomerDTO;
import com.bookstore.bookstore.dto.customer.EmailCustomerDTO;
import com.bookstore.bookstore.dto.customer.ICustomer;
import com.bookstore.bookstore.model.customer.Customer;
import com.bookstore.bookstore.model.email.Email;
import com.bookstore.bookstore.service.customer.ICustomerService;
import com.bookstore.bookstore.service.emailService.IEmailService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/sendingMail")
//@CrossOrigin(value = "http://localhost:4200/", allowCredentials = "true")
public class SendingMailController {


    @Autowired
    IEmailService sendingEmailService;

    @Autowired
    ICustomerService iCustomerService;

    @PostMapping("/sendMail")
    public ResponseEntity<Object> sendMail(@RequestBody List<CustomerDTO> customerDTO, BindingResult bindingResult) {
        try {
            new CustomerDTO().validate(customerDTO, bindingResult);
            if (bindingResult.hasErrors()) {
                String mes = "Không có dữ liệu";
                return new ResponseEntity<>(mes, HttpStatus.NO_CONTENT);
            }
            sendingEmailService.create(customerDTO);
            String mes = "Kiểm tra email nào";
            return new ResponseEntity<>(mes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            String mes = "Có vấn đề";
            return new ResponseEntity<>(mes, HttpStatus.OK);
        }
    }

    @GetMapping("/CustomerList")
    public ResponseEntity<Object> sendCustomerMail() {
        List<ICustomer> customer= iCustomerService.getListCustomerMail();
        if (customer.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
}
