package com.example.employee.service;

import com.example.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void save(Employee employee);

    Employee getById(Integer id);

    List<Employee> getAll();

    void delete(Integer id);
}
