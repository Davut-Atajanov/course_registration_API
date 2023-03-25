package com.example.course_registration_api.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {
    @Schema( example = "Davut", name = "name", description = "Username")
    @NotEmpty(message = "Should not be empty")
    private String name;

    @Schema( example = "YourPassword", name = "password", description = "User's password")
    @NotEmpty(message = "Should not be empty")
    private String password;
}
