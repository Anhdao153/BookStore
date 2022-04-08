package com.bookstore.bookstore.dto.oderDetail;

import com.bookstore.bookstore.model.book.Book;
import com.bookstore.bookstore.model.order.Order;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderDetailDTO implements Validator {
    private long id;
    @Min(1)
    private int quantity;
    @NotNull
    private Book book;
    private double price = book.getPrice()*quantity;
    @NotNull
    private Order order;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(long id, int quantity, Book book, double price, Order order) {
        this.id = id;
        this.quantity = quantity;
        this.book = book;
        this.price = price;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
