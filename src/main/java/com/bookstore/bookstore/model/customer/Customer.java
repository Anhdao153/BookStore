package com.bookstore.bookstore.model.customer;

import com.bookstore.bookstore.config.CustomId;
import com.bookstore.bookstore.model.order.Order;
import com.bookstore.bookstore.model.user.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity(name = "customers")
@SQLDelete(sql = "UPDATE customers SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_seq")
    @GenericGenerator(
            name = "customers_seq",
            strategy = "com.bookstore.bookstore.config.CustomId",
            parameters = {
                    @Parameter(name = CustomId.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = CustomId.VALUE_PREFIX_PARAMETER, value = "KH-"),
                    @Parameter(name = CustomId.NUMBER_FORMAT_PARAMETER, value = "%04d")})
    private String id;
    private String name;
    private int age;
    private String address;
    private int phoneNumber;
    private String email;
    @OneToOne(targetEntity = AppUser.class, cascade = CascadeType.ALL)
    @JsonBackReference
    private AppUser appUser;

    @OneToMany(mappedBy = "customer")
//    ,fetch = FetchType.EAGER   //xài vào thì bị lỗi, k xài thì k bị lỗi
    @JsonBackReference
    private List<Order> orderList;
    private Boolean deleted = Boolean.FALSE;


    public Customer() {
    }

    public Customer(String id, String name, int age, String address, int phoneNumber, String email, AppUser appUser,
                    List<Order> orderList, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.appUser = appUser;
        this.orderList = orderList;
        this.deleted = deleted;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    // TODO: 02/03/2022 by thiện nhỏ

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", appUser=" + appUser +
                ", deleted=" + deleted +
                '}';
    }
}