package com.bookstore.bookstore.model.orderDetail;

import com.bookstore.bookstore.model.book.Book;
import com.bookstore.bookstore.model.order.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;


@Entity(name = "orderdetails")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;
    private double price;
    @ManyToOne(targetEntity = Book.class)
    private Book book;
    @ManyToOne(targetEntity = Order.class)
    private Order order;

    public OrderDetail() {
    }

    public OrderDetail(long id, int quantity, double price, Book book, Order order) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.book = book;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", book=" + book +
                ", order=" + order +
                '}';
    }
}
