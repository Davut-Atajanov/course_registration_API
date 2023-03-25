package com.example.course_registration_api.service;

import com.example.course_registration_api.payload.student.StudentDto;
import com.example.course_registration_api.payload.student.StudentReturnDto;

import java.util.List;

public interface StudentService {
    StudentReturnDto registerStudent(StudentDto studentDto);

    List<StudentReturnDto> getAllStudent();

    StudentReturnDto getStudentById(Long id);

    StudentReturnDto updateStudent(StudentDto studentDto);

    String deleteStudent(Long id);
}
