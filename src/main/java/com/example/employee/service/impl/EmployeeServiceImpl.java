package com.example.employee.service.impl;

import com.example.employee.entity.Employee;
import com.example.employee.exception.EmployeeServiceNotFoundException;
import com.example.employee.exception.EmployeeServiceNotMatchIdUpdateException;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public void save(@Valid Employee employee) {
        log.info("EmployeeServiceImpl save {}", employee);
        repository.save(employee);
    }

    @Override
    public Employee getById(Integer id) {
        log.info("EmployeeServiceImpl get by id: {}", id);
        return repository.findById(id).
                orElseThrow(() -> new EmployeeServiceNotFoundException(" Object with index " + id + " not found"));
    }

    @Override
    public List<Employee> getAll() {
        log.info("EmployeeServiceImpl find ALL");
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        log.info("EmployeeServiceImpl delete by id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public void update(Employee employee, Integer id) {
        log.info("EmployeeServiceImpl update with id: {}", id);
        Employee employeeFromBase=getById(id);
        if (employee.getEmployeeId()!=id){
            throw new EmployeeServiceNotMatchIdUpdateException(" Object from request has index "+ employee.getEmployeeId()+" and doesnt match index from url "+ id);
        }
        save(employee);
    }
}
