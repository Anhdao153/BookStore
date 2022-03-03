package com.bookstore.bookstore.repository.employee;

import com.bookstore.bookstore.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepo extends JpaRepository<Employee, String> {
}
