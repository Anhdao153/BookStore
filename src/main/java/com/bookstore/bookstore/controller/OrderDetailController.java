package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.dto.oderDetail.OrderDetailDTO;
import com.bookstore.bookstore.model.order.Order;
import com.bookstore.bookstore.model.orderDetail.OrderDetail;
import com.bookstore.bookstore.service.orderDetail.IOrderDetailService;
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
@RequestMapping("/api/orderDetail")
public class OrderDetailController {

    @Autowired
    IOrderDetailService iOrderDetailService;

    // khi api add cuar order detail duoc goi thi no se link 2 doi duong book va customer - customer thi chi co 1
// nhung book phair la 1 list.
    //phair get 1 doi tuong voi list book
    // Chỉ khi nào payment thành công thì mới update phướng thức này xuống phía DB
    @PostMapping("/add")
    public ResponseEntity<Object> createOrderDetail(@RequestBody @Valid OrderDetailDTO orderDetailDTO, BindingResult bindingResult) {
        new OrderDetailDTO().validate(orderDetailDTO, bindingResult);
       if (bindingResult.hasErrors()){
           String mes="Ngu lắm thiện";
           return new ResponseEntity<>(mes,HttpStatus.BAD_REQUEST);
       }
       iOrderDetailService.saveOrderDetail(orderDetailDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Có thể làm lịch sử giao dịch, cũng như làm chi tiết hóa đơn của từng khách hàng.
    @GetMapping("/detail")
    public ResponseEntity<Object> getOrderDetail(@RequestParam("id") String id, @PageableDefault Pageable pageable) {

        if (iOrderDetailService.findOrderDetailByOrderId(id, pageable).isEmpty()) {
            String mes = "Ngu lắm thiện";
            return new ResponseEntity<>(mes, HttpStatus.OK);
        }
        Page<OrderDetail> orderDetail = iOrderDetailService.findOrderDetailByOrderId(id, pageable);
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<Object> getOrderDetailById(@RequestParam("id") Long id){
        Optional<OrderDetail> orderDetail= iOrderDetailService.findOrderDetailById(id);
        if (!orderDetail.isPresent()) {
            String mes = "Ngu lắm thiện";
            return new ResponseEntity<>(mes, HttpStatus.OK);
        }
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }
    @PatchMapping("/edit")
    public ResponseEntity<Object> editOrderDetail(@RequestBody @Valid OrderDetailDTO orderDetailDTO, BindingResult bindingResult){
        new OrderDetailDTO().validate(orderDetailDTO, bindingResult);
        if (bindingResult.hasErrors()){
            String mes="Ngu lắm thiện";
            return new ResponseEntity<>(mes,HttpStatus.BAD_REQUEST);
        }
        OrderDetail orderDetail=iOrderDetailService.saveOrderDetail(orderDetailDTO);
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }
}
