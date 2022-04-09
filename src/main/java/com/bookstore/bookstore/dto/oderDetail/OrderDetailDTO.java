package com.bookstore.bookstore.dto.oderDetail;

import com.bookstore.bookstore.model.book.Book;
import com.bookstore.bookstore.model.order.Order;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderDetailDTO implements Validator {
    @Min(1)
    private Integer quantity;
    @NotNull
    private String bookId;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(Integer quantity, String bookId) {
        this.quantity = quantity;
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
