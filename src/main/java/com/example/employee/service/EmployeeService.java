package com.example.employee.service;

import com.example.employee.entity.Employee;

import javax.validation.Valid;
import java.util.List;

public interface EmployeeService {
    void save(@Valid Employee employee);

    Employee getById(Integer id);

    List<Employee> getAll();

    void delete(Integer id);
}
