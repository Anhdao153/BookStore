package com.bookstore.bookstore.dto.book;

import com.bookstore.bookstore.model.orderDetail.OrderDetail;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class BookDTO implements Validator {

    String id;
    @NotNull(message = "Tên sách không được để trống")
    private String name;
    @NotNull(message = "Tác giả không được để trống")
    private String author;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate publication;
    @NotNull
    private String publishing_company;
    @NotNull
    private String translator;
    @Min(1)
    private int quantity;
    @NotNull
    private double price;
    private List<OrderDetail> orderDetail;

    private String url;

    public BookDTO() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BookDTO(String id, String name, String author, LocalDate publication, String publishing_company, String translator, int quantity, double price, List<OrderDetail> orderDetail, String url) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.publishing_company = publishing_company;
        this.translator = translator;
        this.quantity = quantity;
        this.price = price;
        this.orderDetail = orderDetail;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublication() {
        return publication;
    }

    public void setPublication(LocalDate publication) {
        this.publication = publication;
    }

    public String getPublishing_company() {
        return publishing_company;
    }

    public void setPublishing_company(String publishing_company) {
        this.publishing_company = publishing_company;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
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

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publication=" + publication +
                ", publishing_company='" + publishing_company + '\'' +
                ", translator='" + translator + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
