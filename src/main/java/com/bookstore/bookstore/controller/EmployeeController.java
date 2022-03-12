package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.dto.Employee.EmployeeDTO;
import com.bookstore.bookstore.model.employee.Employee;
import com.bookstore.bookstore.repository.employee.IEmployeeRepo;
import com.bookstore.bookstore.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    IEmployeeService iEmployeeService;

    @GetMapping("/list")
    public ResponseEntity<Page<Employee>> getListEmployee(@RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                                          @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection,
                                                          @RequestParam(defaultValue = "") String id,
                                                          @RequestParam(defaultValue = "") String email,
                                                          @RequestParam(defaultValue = "") int age,
                                                          @RequestParam(defaultValue = "") int phone,
                                                          @RequestParam(defaultValue = "") String address,
                                                          @RequestParam(defaultValue = "") String dateOfBirth,
                                                          @RequestParam(defaultValue = "") String name) {
        int size = 5;
        Page<Employee> employeePage = iEmployeeService.getListEmployee(page, size, phone, age, id, email, dateOfBirth, address, name, sortField, sortDirection);
        if (employeePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> saveEmployee(EmployeeDTO employeeDTO, BindingResult bindingResult) {
        new EmployeeDTO().validate(employeeDTO,bindingResult);
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        iEmployeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
