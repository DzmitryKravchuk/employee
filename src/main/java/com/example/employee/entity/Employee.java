package com.example.employee.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

@Schema(description = "Модель сотрудника")
@Data
public class Employee {
    @Id
    @Schema(description = "Идентификатор сотрудника", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer employeeId;
    @Schema(description = "Имя", example = "Иван")
    @NotBlank
    private String firstName;
    @Schema(description = "Фамилия", example = "Иванов")
    @NotBlank
    private String lastName;
    @Schema(description = "Идентификатор отдела", example = "1")
    @Min(1)
    @Max(10)
    private Integer departmentId;
    @NotBlank
    @Schema(description = "Профессия", example = "водитель")
    private String jobTitle;
    @NotBlank
    @Schema(description = "пол", example = "м")
    private Character gender;
    @Past
    @Schema(description = "Дата рождения", example = "1986-04-26")
    private Date dateOfBirth;
}
