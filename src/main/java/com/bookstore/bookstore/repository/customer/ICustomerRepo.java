package com.bookstore.bookstore.repository.customer;

import com.bookstore.bookstore.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer,String> {

    @Query(value="SELECT * from customers where id like concat('%',:customersId,'%')" +
            " and email like concat('%',:email,'%') " +
            "and age like concat('%',:age,'%')" +
            "and address like concat('%',:address,'%')" +
            "and phoneNumber like concat('%',phoneNumber,'%') and order_id like concat('%',order_id,'%') ", nativeQuery = true)
    Page<Customer> getListCustomer(@Param("customerId")String customerId,
                                   @Param("email")String email,
                                   @Param("age")int age,
                                   @Param("address")String address ,
                                   @Param("order_id")String order_id ,
                                   @Param("phoneNumber")int phoneNumber, Pageable pageable);
}
