package com.example.employee.exception;

public class EmployeeServiceNotFoundException extends RuntimeException {
    public EmployeeServiceNotFoundException(String message) {
        super(message);
    }
}
