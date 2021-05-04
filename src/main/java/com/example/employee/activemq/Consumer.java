package com.example.employee.activemq;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@Component
public class Consumer {
    private final EmployeeService employeeService;

    @JmsListener(destination = "${spring.activemq.queue}")
    public void receiveMessage(String employeeString) throws ParseException {
        log.info("Received {}", employeeString);
        Employee employee = parseJsonString(employeeString);
        employeeService.save(employee);
    }

    private Employee parseJsonString(String employeeString) throws ParseException {
        Employee entity = new Employee();
        JSONObject parsedObject = new JSONObject(employeeString);
        entity.setFirstName(parsedObject.getString("firstName"));
        entity.setLastName(parsedObject.getString("lastName"));
        entity.setDepartmentId(parsedObject.getInt("departmentId"));
        entity.setJobTitle(parsedObject.getString("jobTitle"));
        entity.setGender(parsedObject.getString("gender").charAt(0));

        Date date = getDate(parsedObject);
        entity.setDateOfBirth(date);
        return entity;
    }

    private Date getDate(JSONObject parsedObject) throws ParseException {
        Long dateLong = parsedObject.getLong("dateOfBirth");
        Date date = new Date(dateLong);
        return date;
    }
}
