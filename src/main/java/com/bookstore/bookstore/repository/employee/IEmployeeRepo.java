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

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, String> {

    @Query(value = "select * from employees e where " +
            "e.id like  concat ('%', :id, '%')" +
            " and e.name like concat('%',:name,'%') " +
            "and e.email like concat('%', :email, '%') " +
            "and e.address like concat ('%',:address,'%') " +
            "and e.dayOfBirth like concat ('%',:dayOfBirth,'%') " +
            "and e.age like concat ('%', :age,'%') " +
            "and e.phoneNumber like concate ('%',:phoneNumber, '%') ", nativeQuery = true)
    Page<Employee> getListEmployee(@Param("id")String id,
                                   @Param("name") String name,
                                   @Param("email") String email,
                                   @Param("address") String address,
                                   @Param("dayOfBirth") String dayOfBirth,
                                   @Param("age") int age,
                                   @Param("phoneNumber") int phoneNumber,
                                   Pageable pageable
                                   );
    @Modifying
    @Transactional
    @Query(value = "update employees a set deleted =1 where a.id = :id",nativeQuery = true)
    void deleteEmployeeById(@Param("id") String id);
}
