package com.example.course_registration_api.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthDto {
    @Schema( example = "22001122",name = "userId",description = "University ID")
    private long userId;
    @Schema( example = "String123!,.",name = "password",description = "Password")
    private String password;
}