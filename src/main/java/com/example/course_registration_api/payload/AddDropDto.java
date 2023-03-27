package com.example.course_registration_api.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AddDropDto {
    @Schema( example = "101",name = "courseCode",description = "Course Code")
    @NotNull
    private int courseCode;
    @NotEmpty
    @Schema( example = "[22001479,22001475,22001462]",name = "studentUniversityIds",description = "Student university ids")
    private List<Integer> studentUniversityIds;
}
