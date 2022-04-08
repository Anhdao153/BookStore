package com.bookstore.bookstore.service.employee;

import com.bookstore.bookstore.dto.Employee.EmployeeDTO;
import com.bookstore.bookstore.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IEmployeeService {
    Optional<Employee> findEmployeeById(String id);

    Employee saveEmployee(EmployeeDTO employeeDTO);

    void deletedEmployee(String id);

    Page<Employee> getListEmployee(String keyWord, Pageable pageable);

}
