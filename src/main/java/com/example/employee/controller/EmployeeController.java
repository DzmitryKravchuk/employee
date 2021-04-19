package com.example.employee.controller;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee/")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        return employeeService.getById(id);
    }

    @PostMapping("")
    public Employee saveEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return employeeService.getById(employee.getEmployeeId());
    }

    @PutMapping("")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return employeeService.getById(employee.getEmployeeId());
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.delete(id);
    }
}
