package com.bookstore.bookstore.repository.employee;

import com.bookstore.bookstore.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import java.util.Optional;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, String> {
    //    e.id, e.address, e.email, e.name, e.deleted, e.age, e.phone_number, e.app_user_id,e.day_of_birth
    @Query(value = "select * from employees e" +
            " where deleted = 0 and concat (id,address,email,`name`) like ?1",
            countQuery = "select count(*) from employees " +
                    " where employees.deleted = 0 and concat (id,address,email,`name`) like ?1",
            nativeQuery = true)
    Page<Employee> getListEmployee(String keyWord, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update employees a set deleted =1 where a.id = :id", nativeQuery = true)
    void deleteEmployeeById(@Param("id") String id);

    @Query(value = "SELECT * from employees where deleted = 0 and employees.id like ?1",
                countQuery = "SELECT count (*) from employees where deleted = 0 and employees.id like ?1",
            nativeQuery = true)
    Optional<Employee> findEmployeeById(String id);
}
