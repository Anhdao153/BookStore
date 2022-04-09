package com.bookstore.bookstore.service.orderDetail.Impl;

import com.bookstore.bookstore.dto.oderDetail.ListOrder;
import com.bookstore.bookstore.dto.oderDetail.OrderDetailDTO;
import com.bookstore.bookstore.model.book.Book;
import com.bookstore.bookstore.model.order.Order;
import com.bookstore.bookstore.model.orderDetail.OrderDetail;
import com.bookstore.bookstore.repository.book.IBookRepo;
import com.bookstore.bookstore.repository.customer.ICustomerRepo;
import com.bookstore.bookstore.repository.order.IOrderRepo;
import com.bookstore.bookstore.repository.orderDetail.IOrderDetailRepo;
import com.bookstore.bookstore.service.orderDetail.IOrderDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OderDetailServiceImpl implements IOrderDetailService {

    @Autowired
    IOrderDetailRepo iOrderDetailRepo;

    @Autowired
    IOrderRepo iOrderRepo;

    @Autowired
    ICustomerRepo iCustomerRepo;

    @Autowired
    IBookRepo iBookRepo;

    @Override
    public Page<OrderDetail> findOrderDetailByOrderId(String id, Pageable pageable) {
        return iOrderDetailRepo.findOrderDetailByOrderId(id, pageable);
    }

    // Phương thức tạo ra hóa đơn chi tiết, nó sẽ tự động tạo ra 1 hóa đơn mới và gắn các thuộc tính vào
    @Override
    public String saveOrderDetail(ListOrder orderDetailDTO) {
        Order order = new Order(iCustomerRepo.findById(orderDetailDTO.getCustomerId()).get());
        Order order2 = iOrderRepo.save(order);
        List<OrderDetailDTO> list = orderDetailDTO.getOrderDetailDTOS();
        LocalDateTime localDate = LocalDateTime.now();
        // Kiểm tra xem số lượng sách trong kho là bao nhiêu, có hết hàng không
        for (OrderDetailDTO items : list) {
            Book check = iBookRepo.findById(items.getBookId()).get();
            check.setQuantity(check.getQuantity() - items.getQuantity());
            System.out.println(check.getQuantity());
            if (check.getQuantity() < 0) {
                String mes = "Số lượng sách trong kho không đủ";
                return mes;
            }
            /* Khi kiểm tra phải cộng lại, vì trong java không có khái niệm tham chiếu
             khi trừ giá trị của đối tượng, thì đối tượng trong vùng nhớ heap bị thay đổi
             Mình tạo đối tượng ra thì cũng chỉ là 1 biến mới, trỏ tới vị trí
             hiện tại của thằng iBookRepo.findById(items.getBookId()).get(); này mà thôi */
            check.setQuantity(check.getQuantity() + items.getQuantity());
        }
        // Thêm mới hóa đơn và giảm số lượng sách trong table sách xuống
        for (OrderDetailDTO items : list) {
            Book book = iBookRepo.findById(items.getBookId()).get();
            book.setQuantity(book.getQuantity() - items.getQuantity());
            System.out.println(book.getQuantity());
            iBookRepo.save(book);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setBook(book);
            orderDetail.setOrder(order2);
            orderDetail.setQuantity(items.getQuantity());
            orderDetail.setPrice(items.getQuantity() * book.getPrice());
            orderDetail.setPayDay(localDate);
            iOrderDetailRepo.save(orderDetail);
        }
        return null;
    }

    @Override
    public Optional<OrderDetail> findOrderDetailById(Long id) {
        return iOrderDetailRepo.findById(id);
    }

    @Override
    public Page<OrderDetail> findOrderDetailByCustomerId(String id, Pageable pageable) {
        return iOrderDetailRepo.findOrderDetailByCustomerId(id, pageable);
    }

    @Override
    public Page<OrderDetail> findAllOrderDetail(Pageable pageable) {
        return iOrderDetailRepo.findAllOrderDetail(pageable);
    }
}
