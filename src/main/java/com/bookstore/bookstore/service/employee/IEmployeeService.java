package com.bookstore.bookstore.service.employee;

import com.bookstore.bookstore.dto.Employee.EmployeeDTO;
import com.bookstore.bookstore.model.employee.Employee;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IEmployeeService {
    Optional<Employee> findEmployeeById(String id);

    Employee saveEmployee(EmployeeDTO employeeDTO);

    void deletedEmployee(String id);

    Page<Employee> getListEmployee(int page, int size, int phone, int age, String id, String email, String dateOfBirth,
                                   String address, String name, String sortField, String sortDirection);

}
