package com.example.course_registration_api.payload;

import lombok.Data;

@Data
public class StudentRegisterDto {
    private long id;
    private String name;
    private String surname;
    private float cgpa;
    private String department;
    private int year;
    private long studentId;
    private String password;
}
