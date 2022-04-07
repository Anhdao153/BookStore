package com.bookstore.bookstore.model.employee;

import com.bookstore.bookstore.config.CustomId;
import com.bookstore.bookstore.model.user.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity(name = "employees")
@SQLDelete(sql = "UPDATE employees SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_seq")
    @GenericGenerator(
            name = "employees_seq",
            strategy = "com.bookstore.bookstore.config.CustomId",
            parameters = {
                    @Parameter(name = CustomId.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = CustomId.VALUE_PREFIX_PARAMETER, value = "EM-"),
                    @Parameter(name = CustomId.NUMBER_FORMAT_PARAMETER, value = "%04d")})
    private String id;
    private String name;
    private int age;
    private String dayOfBirth;
    private String address;
    private int phoneNumber;
    private String email;
    @OneToOne(targetEntity = AppUser.class, cascade = CascadeType.PERSIST)
    private AppUser appUser;
    private Boolean deleted = Boolean.FALSE;

    public Employee() {
    }

    public Employee(String id, String name, int age, String dayOfBirth, String address, int phoneNumber, String email, AppUser appUser, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dayOfBirth = dayOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.appUser = appUser;
        this.deleted = deleted;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
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
    // TODO: 03/03/2022

    @Override
    public String toString() {
        return "Employee{" +
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
