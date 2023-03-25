package com.example.course_registration_api.payload.student;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDto {
    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @Schema(example = "password", name = "password", description = "Your password")
    @NotEmpty
    private String password;
    @Schema( example = "3.5",name = "cgpa",description = "Student's CGPA")
    @NotNull
    private float cgpa;
    @Schema( example = "CTIS", name = "department", description = "Student's Department")
    @NotEmpty(message = "Should not be empty")
    private String department;
    @NotNull
    private int year;
    @NotNull
    private long universityId;
}
