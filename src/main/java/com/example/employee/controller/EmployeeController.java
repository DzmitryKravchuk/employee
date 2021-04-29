package com.example.employee.controller;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/employee/")
@Tag(name = "Сотрудники", description = "Взаимодействие с базой сотрудников")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping()
    @Operation(summary = "Вывод информации по всем сотрудникам")
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Запрос по конкретному сотруднику", description = "Позволяет получить данные работника по его id")
    public Employee getEmployee(@PathVariable @Parameter(description = "Идентификатор пользователя") @Min(1) Integer id) {
        return employeeService.getById(id);
    }

    @PostMapping()
    @Operation(summary = "Создание нового сотрудника", description = "Позволяет внести данные о работнике в систему")
    public Employee saveEmployee( @RequestBody Employee employee) {
        employeeService.save(employee);
        return employeeService.getById(employee.getEmployeeId());
    }

    @PutMapping()
    @Operation(summary = "Обновление информации о сотруднике")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.getById(employee.getEmployeeId());
        employeeService.save(employee);
        return employeeService.getById(employee.getEmployeeId());
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удалить сотрудника", description = "Позволяет удалить данные работника по его id")
    public void deleteEmployee(@PathVariable @Parameter(description = "Идентификатор пользователя") @Min(1) Integer id) {
        employeeService.delete(id);
    }
}
