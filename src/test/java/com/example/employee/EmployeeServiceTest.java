package com.example.employee;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Transactional
@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

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
    public void getAllEmployeeTest() {
        List<Employee> entityFromBaseList = employeeService.getAll();
        assertEquals(entityFromBaseList.size(), 7);
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
        entity.setDepartmentId(getRandomInt());
        entity.setDateOfBirth(setDate());
        employeeService.save(entity);
        return entity;
    }
}
