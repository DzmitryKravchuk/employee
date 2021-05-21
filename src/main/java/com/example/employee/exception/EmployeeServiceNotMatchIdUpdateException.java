package com.example.employee.exception;

public class EmployeeServiceNotMatchIdUpdateException extends RuntimeException {

    public EmployeeServiceNotMatchIdUpdateException(String message) {
        super(message);
    }
}
