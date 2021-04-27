package com.example.employee.validation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Getter
@RequiredArgsConstructor
@Schema(description = "Ответ при нарушении валидации")
public class ValidationErrorResponse {
    private final List<Violation> violations;
}


