package com.bookstore.bookstore.repository.customer;

import com.bookstore.bookstore.dto.customer.ICustomerDTO;
import com.bookstore.bookstore.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ICustomerRepo extends JpaRepository<Customer, String> {

    @Query(value = "SELECT * from customers " +
            "join orders on customers.id = orders.customer_id " +
            "where customers.id like concat('%',:id,'%')" +
            "and email like concat('%',:email,'%') " +
            "and age like concat('%',:age,'%')" +
            "and address like concat('%',:address,'%')" +
            "and phone_number like concat('%',:phone_number,'%')" +
            "and orders.id like concat('%',:order_id,'%')", nativeQuery = true)
    Page<Customer> getListCustomer(@Param("id") String id,
                                   @Param("email") String email,
                                   @Param("age") int age,
                                   @Param("address") String address,
                                   @Param("phone_number") int phoneNumber,
                                   @Param("order_id") String order_id,
                                   Pageable pageable);

    @Query(value = "select c.id customerId, o.id orderId from customers c " +
            "join orders o on c.id = o.customer_id " +
            "join app_users au on au.id = c.app_user_id  where c.id=?1",nativeQuery = true)
    List<ICustomerDTO> findCustomerById(String id);

}
