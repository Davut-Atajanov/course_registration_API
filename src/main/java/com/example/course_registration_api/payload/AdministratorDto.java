package com.example.course_registration_api.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdministratorDto {
    @Schema( example = "String123!,.",name = "password",description = "Password")
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @Schema( example = "CTIS", name = "department", description = "Administrator's Department")
    @NotEmpty(message = "Should not be empty")
    private String department;
    @NotNull
    private int universityId;
}
