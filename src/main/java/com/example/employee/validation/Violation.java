package com.example.employee.validation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@Schema(description = "Невалидные данные для поля")
public class Violation {
    private final String fieldName;
    private final String message;
}
