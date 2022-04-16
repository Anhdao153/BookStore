package com.bookstore.bookstore.repository.customer;

import com.bookstore.bookstore.dto.customer.EmailCustomerDTO;
import com.bookstore.bookstore.dto.customer.ICustomer;
import com.bookstore.bookstore.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ICustomerRepo extends JpaRepository<Customer, String> {

    @Query(value = "Select * from customers " +
            "where deleted = 0 and concat(id, phone_Number, name, email, address) like ?1 ",
            countQuery = "Select count(*) from customers " +
                    "where deleted = 0 and concat(id, phone_Number, name, email, address) like ?1 ",
            nativeQuery = true)
    Page<Customer> showListCustomer(String keyWord, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update customers a set a.deleted =1 where a.id=?1", nativeQuery = true)
    void deleteCustomerById(String id);


    @Query(value = "Select `name`,email from customers", nativeQuery = true)
    List<ICustomer> customerSendMail();
}
