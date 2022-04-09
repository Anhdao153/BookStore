package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.dto.oderDetail.ListOrder;
import com.bookstore.bookstore.dto.oderDetail.OrderDetailDTO;
import com.bookstore.bookstore.model.orderDetail.OrderDetail;
import com.bookstore.bookstore.service.order.IOrderService;
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

    @Autowired
    IOrderService iOrderService;

    // Thêm hóa đơn cùng với thời gian thanh toán.
    @PostMapping(value = "/add")
    public ResponseEntity<Object> createOrderDetail(@RequestBody @Valid ListOrder orderDetailDTO, BindingResult bindingResult) {
        new ListOrder().validate(orderDetailDTO,bindingResult);
        if (bindingResult.hasErrors()) {
            String mes = "Ngu lắm thiện";
            System.out.println(mes);
            return new ResponseEntity<>(mes, HttpStatus.BAD_REQUEST);
        }
        if (iOrderDetailService.saveOrderDetail(orderDetailDTO) == null) {
            String mes = "Thêm mới thành công";
            return new ResponseEntity<>(mes, HttpStatus.OK);
        }
        String mes = "Thêm mới thất bại, số lượng sách trong kho không đủ";
        return new ResponseEntity<>(mes, HttpStatus.BAD_REQUEST);
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

    // Chi tiết từng sản phẩm mua
    @GetMapping("/id")
    public ResponseEntity<Object> getOrderDetailByOrderId(@RequestParam("id") Long id) {
        Optional<OrderDetail> orderDetail = iOrderDetailService.findOrderDetailById(id);
        if (!orderDetail.isPresent()) {
            String mes = "Ngu lắm thiện";
            return new ResponseEntity<>(mes, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }

    //Danh sách toàn bộ hóa đơn của 1 khách hàng.
    @GetMapping("/orderList")
    public ResponseEntity<Object> getOrderDetailByCustomerId(@RequestParam("id") String id, @PageableDefault Pageable pageable) {
        Page<OrderDetail> orderDetail = iOrderDetailService.findOrderDetailByCustomerId(id, pageable);
        if (orderDetail.isEmpty()) {
            String mes = "Ngu lắm thiện";
            return new ResponseEntity<>(mes, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }

    //Danh sách toàn bộ hóa đơn của tất cả khách hàng.
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllOrderDetail(@PageableDefault Pageable pageable) {
        Page<OrderDetail> orderDetail = iOrderDetailService.findAllOrderDetail(pageable);
        if (orderDetail.isEmpty()) {
            String mes = "Ngu lắm thiện";
            return new ResponseEntity<>(mes, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }
}
