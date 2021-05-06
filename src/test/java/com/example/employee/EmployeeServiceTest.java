package com.example.employee;

import com.example.employee.entity.Employee;
import com.example.employee.exception.EmployeeServiceNotFoundException;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Transactional
@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    protected static final Random RANDOM = new Random();

    @Test
    public void createEmployeeTest() {
        Employee employee = createNewEmployee();
        Employee entityFromBase = employeeService.getById(employee.getEmployeeId());
        assertNotNull(entityFromBase.getEmployeeId());
        assertEquals(employee.getFirstName(), entityFromBase.getFirstName());
        employeeService.delete(employee.getEmployeeId());
    }

    @Test
    public void exceptionTest() {
        Employee employee = createNewEmployee();
        employee.setGender(null);
        assertThrows(ConstraintViolationException.class, () -> {
            employeeService.save(employee); // throws exception
        });
        assertThrows(EmployeeServiceNotFoundException.class, () -> {
            employeeService.getById(-getRandomInt()); // throws exception
        });

    }

    @Test
    public void getAllEmployeeTest() {
        List<Employee> entityFromBaseList = employeeService.getAll();
        assertEquals(entityFromBaseList.size(), 7);
    }

    @Test
    public void getByNameTest() {
        Employee employee = createNewEmployee();
        Employee entityFromBase = employeeRepository.findByFirstName(employee.getFirstName());
        assertEquals(entityFromBase.getFirstName(), employee.getFirstName());
    }

    @Test
    public void getByNameAndSurnameTest() {
        Employee employee = createNewEmployee();
        Employee entityFromBase = employeeRepository.findByFirstNameAndLastName(employee.getFirstName(),employee.getLastName());
        assertEquals(entityFromBase.getFirstName(), employee.getFirstName());
        assertEquals(entityFromBase.getLastName(), employee.getLastName());
    }


    private Integer getRandomInt() {
        return RANDOM.nextInt(99);
    }

    private java.util.Date setDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2021 - getRandomInt(), Calendar.JANUARY, 1);
        return cal.getTime();
    }

    private Employee createNewEmployee() {
        Employee entity = new Employee();
        entity.setFirstName("First" + getRandomInt());
        entity.setLastName("Last" + getRandomInt());
        entity.setGender('М');
        entity.setJobTitle("Job" + getRandomInt());
        entity.setDepartmentId(1);
        entity.setDateOfBirth(setDate());
        employeeService.save(entity);
        return entity;
    }
}
