package com.example.course_registration_api.controller;


import com.example.course_registration_api.payload.AdministratorDto;
import com.example.course_registration_api.payload.AuthDto;
import com.example.course_registration_api.payload.course.CourseReturnDto;
import com.example.course_registration_api.payload.student.StudentDto;
import com.example.course_registration_api.service.AuthService;
import com.example.course_registration_api.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
@Tag(name = "Auth Controller", description = "Operations related to Authentication")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }


    @PostMapping("register")
    @Operation(summary = "Register as administarator")
    public ResponseEntity<AdministratorDto> register(@Valid @RequestBody AdministratorDto administratorDto){
        return new ResponseEntity<>(this.authService.register(administratorDto), HttpStatus.CREATED);
    }

    @PostMapping("login")
    @Operation(summary = "Login as administarator")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody AuthDto authDto){
        String token = authService.login(authDto);
        if (token.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
