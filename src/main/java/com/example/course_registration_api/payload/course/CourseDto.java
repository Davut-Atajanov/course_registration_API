package com.example.course_registration_api.payload.course;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseDto {
    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    @Schema(example = "davut", name = "instructorName", description = "Name of the instructor")
    private String instructorName;
    @Schema(example = "2", name = "sections", description = "Number of sections")
    @NotNull
    private int sections;
    @Schema( example = "101",name = "courseCode",description = "Course code")
    @NotNull
    private int courseCode;
    @Schema( example = "CTIS", name = "department", description = "Student's Department")
    @NotEmpty(message = "Should not be empty")
    private String department;
}
