package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.dto.Employee.EmployeeDTO;
import com.bookstore.bookstore.model.employee.Employee;
import com.bookstore.bookstore.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    IEmployeeService iEmployeeService;

    @GetMapping("/detail")
    public ResponseEntity<Object> detailEmployee(@RequestParam(name ="id") String id){
        if (!iEmployeeService.findEmployeeById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Employee> employee = iEmployeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Employee>> getListEmployee(@PageableDefault(value = 10) Pageable pageable,
                                                          @RequestParam(name = "keyWord", defaultValue = "") String keyWord) {

        Page<Employee> employeePage = iEmployeeService.getListEmployee(keyWord, pageable);
        if (employeePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeePage, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> saveEmployee(EmployeeDTO employeeDTO, BindingResult bindingResult) {
        new EmployeeDTO().validate(employeeDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Employee employee = iEmployeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @PatchMapping("/edit")
    public ResponseEntity<Object> editEmployee(EmployeeDTO employeeDTO, BindingResult bindingResult) {
        new EmployeeDTO().validate(employeeDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            String mes = "Lỗi nhập ngu";
            return new ResponseEntity<>(mes, HttpStatus.BAD_REQUEST);
        }
        iEmployeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(employeeDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletedEmployee(@RequestParam("id")String id){
        if (!iEmployeeService.findEmployeeById(id).isPresent()){
            String mes= "Thứ mày muốn xóa không tồn tại";
            return new ResponseEntity<>(mes, HttpStatus.BAD_REQUEST);

        }
        iEmployeeService.deletedEmployee(id);
        String mes= "Đã xóa khởi dữ liệu";
        return new ResponseEntity<>(mes, HttpStatus.OK);
    }
}
