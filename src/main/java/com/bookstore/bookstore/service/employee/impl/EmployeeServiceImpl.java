package com.bookstore.bookstore.service.employee.impl;

import com.bookstore.bookstore.dto.Employee.EmployeeDTO;
import com.bookstore.bookstore.model.employee.Employee;
import com.bookstore.bookstore.repository.employee.IEmployeeRepo;
import com.bookstore.bookstore.service.employee.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    IEmployeeRepo iEmployeeRepo;


    @Override
    public Optional<Employee> findEmployeeById(String id) {
        return iEmployeeRepo.findEmployeeById(id);
    }

    @Override
    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return iEmployeeRepo.save(employee);
    }

    @Override
    public void deletedEmployee(String id) {
        iEmployeeRepo.deleteEmployeeById(id);

    }

    @Override
    public Page<Employee> getListEmployee(String keyWord, Pageable pageable) {
        return  iEmployeeRepo.getListEmployee("%"+keyWord+"%", pageable);
    }
}
