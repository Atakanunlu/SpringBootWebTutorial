package com.atakanunlu.dto;

import com.atakanunlu.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name of the employee cannot be Blank.")
    @Size(min = 2 , max = 15, message = "Number of characters in name should be in the range: [2,15]")
    private String name;

    @NotBlank(message = "Email of the employee cannot be blank")
    @Email(message = "Email shoulbe be a valid email")
    private String email;

    @NotNull(message = "Age of the employee cannot be blank")
    @Max(value = 65, message = "Age of employee cannot be greater than 65")
    @Min(value = 18, message = "Age of employee cannot be less than 18")
    private Integer age;

    @NotBlank(message = "Role of the employee cannot be blank")
    //@Pattern(regexp = "^(ADMIN|USER)$",message = "Role of employee can either be USER or ADMIN")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "Age of the employee cannot be blank")
    @Positive(message = "Salary of employee should be positive")
    @Digits(integer = 6, fraction = 2, message = "The salary can be in the from XXXXX.YY")
    @DecimalMax(value = "10000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @PastOrPresent(message = "DateOfJoining field in Employee cannot be future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;

}