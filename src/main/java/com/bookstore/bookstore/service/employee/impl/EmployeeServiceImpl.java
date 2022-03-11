package com.bookstore.bookstore.service.employee.impl;

import com.bookstore.bookstore.dto.Employee.EmployeeDTO;
import com.bookstore.bookstore.model.employee.Employee;
import com.bookstore.bookstore.repository.employee.IEmployeeRepo;
import com.bookstore.bookstore.service.employee.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    IEmployeeRepo iEmployeeRepo;


    @Override
    public Optional<Employee> findEmployeeById(String id) {
        return iEmployeeRepo.findById(id);
    }

    @Override
    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        return iEmployeeRepo.save(employee);
    }

    @Override
    public void deletedEmployee(String id) {
        iEmployeeRepo.deleteEmployeeById(id);

    }

    @Override
    public Page<Employee> getListEmployee(int page, int size, int phone, int age, String id, String email, String dateOfBirth, String address, String name, String sortField, String sortDirection) {
        size =5;


        return null;
    }
}
