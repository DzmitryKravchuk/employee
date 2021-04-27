package com.example.employee.service.impl;

import com.example.employee.entity.Employee;
import com.example.employee.exception.ObjectNotFoundException;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public void save(Employee employee) {
        log.info("EmployeeServiceImpl save {}", employee);
        repository.save(employee);
    }

    @Override
    public Employee getById(Integer id) {
        log.info("EmployeeServiceImpl get by id: {}", id);
        return repository.findById(id).
                orElseThrow(() -> new ObjectNotFoundException(" Object with index " + id + " not found"));
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
}
