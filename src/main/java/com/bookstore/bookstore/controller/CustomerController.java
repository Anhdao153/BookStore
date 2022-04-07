package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.model.customer.Customer;
import com.bookstore.bookstore.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    ICustomerService iCustomerService;

    //lấy tạm cái id ra, sau này làm lịch sử giao dịch thì viết front-end gọi 2 api
    @GetMapping("/id")
    public ResponseEntity<Object> findCustomerById(@RequestParam("id") String id) {
        Optional<Customer> customer = iCustomerService.findCustomerById(id);
        if (!iCustomerService.findCustomerById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    //lấy bảng danh sách employee. lỗi khi nhập dữ liệu vào postman ở age và number vì kiểu dữ liệu int != String
    @GetMapping("/list")
    public ResponseEntity<Page<Customer>> getList(@RequestParam(name = "page", defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "")String id,
                                                  @RequestParam(defaultValue = "")String email,
                                                  @RequestParam(value = "")int age,
                                                  @RequestParam(defaultValue = "")String address,
                                                  @RequestParam(defaultValue = "")String orderId,
                                                  @RequestParam(value = "")int phoneNumber,
                                                  @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                                  @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection) {
        int size = 10;
//        int age2 = Integer.parseInt(age);
//        int phoneNumber2 = Integer.parseInt(phoneNumber);
        Page<Customer> customersPage= this.iCustomerService.getListCustomer(page,size,id,email,age,address,orderId,phoneNumber,sortField,sortDirection);
       if (customersPage.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(customersPage,HttpStatus.OK);

    }
}
