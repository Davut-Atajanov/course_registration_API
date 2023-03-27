package com.example.course_registration_api.payload.student;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class StudentReturnDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @Schema( example = "3.5",name = "cgpa",description = "Student's CGPA")
    @NotNull
    private float cgpa;
    @Schema( example = "CTIS", name = "department", description = "Student's Department")
    @NotEmpty(message = "Should not be empty")
    private String department;
    @NotNull
    private int year;
    @NotNull
    private int universityId;
    private List<String> courses;
}
