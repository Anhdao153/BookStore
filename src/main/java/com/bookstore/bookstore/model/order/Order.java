package com.bookstore.bookstore.model.order;

import com.bookstore.bookstore.config.CustomId;
import com.bookstore.bookstore.model.customer.Customer;
import com.bookstore.bookstore.model.orderDetail.OrderDetail;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity(name = "orders")
@SQLDelete(sql = "UPDATE orders SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @GenericGenerator(
            name = "orders_seq",
            strategy = "com.bookstore.bookstore.config.CustomId",
            parameters = {
                    @Parameter(name = CustomId.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = CustomId.VALUE_PREFIX_PARAMETER, value = "OR-"),
                    @Parameter(name = CustomId.NUMBER_FORMAT_PARAMETER, value = "%04d")})
    private String id;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetail;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

