package com.example.employee.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Schema(description = "Модель сотрудника")
@Data
@Table
public class Employee {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор сотрудника", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer employeeId;

    @Column
    @NotBlank
    @Schema(description = "Имя", example = "Иван")
    private String firstName;

    @Column
    @NotBlank
    @Schema(description = "Фамилия", example = "Иванов")
    private String lastName;

    @Column
    @Schema(description = "Идентификатор отдела")
    @Min(1)
    @Max(10)
    private Integer departmentId;

    @Column
    @NotBlank
    @Schema(description = "Профессия", example = "водитель")
    private String jobTitle;

    @Column
    @NotNull
    @Schema(description = "пол", example = "м")
    private Character gender;

    @Column
    @Past
    @Schema(description = "Дата рождения", example = "1986-04-26")
    private Date dateOfBirth;
}
