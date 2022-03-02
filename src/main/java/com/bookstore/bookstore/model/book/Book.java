package com.bookstore.bookstore.model.book;

import com.bookstore.bookstore.config.CustomId;
import com.bookstore.bookstore.model.orderDetail.OrderDetail;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name= "books")
@SQLDelete(sql = "UPDATE individuals SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_seq")
    @GenericGenerator(
            name = "books_seq",
            strategy = "com.bookstore.bookstore.config.CustomId",
            parameters = {
                    @Parameter(name = CustomId.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = CustomId.VALUE_PREFIX_PARAMETER, value = "BS-"),
                    @Parameter(name = CustomId.NUMBER_FORMAT_PARAMETER, value = "%04d")})
    private String id;
    private String author;
    private String name;
    @Column(name = "publication", columnDefinition = "DATE")
    private LocalDate publication;
    private String publishing_company;
    private String translator;
    private int quantity;
    private int price;
    @OneToMany(mappedBy = "book")
    @JsonBackReference
    private List<OrderDetail> orderDetail;

    public Book() {
    }

    public Book(String id, String author, String name, LocalDate publication, String publishing_company,
                String translator, int quantity, int price, List<OrderDetail> orderDetail) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.publication = publication;
        this.publishing_company = publishing_company;
        this.translator = translator;
        this.quantity = quantity;
        this.price = price;
        this.orderDetail = orderDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", publication=" + publication +
                ", publishing_company='" + publishing_company + '\'' +
                ", translator='" + translator + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
