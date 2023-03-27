package com.example.course_registration_api.controller;

import com.example.course_registration_api.payload.student.StudentDto;
import com.example.course_registration_api.payload.student.StudentReturnDto;
import com.example.course_registration_api.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/students")
@Tag(name = "Student Controller", description = "Operations related with students' profile")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("getAll")
    @Operation(summary = "Get all students")
    public ResponseEntity<List<StudentReturnDto>> getAllStudents(){
        return new ResponseEntity(studentService.getAllStudent(),HttpStatus.OK);
    }

    @GetMapping("get/{universityId}")
    @Operation(summary = "Get a student by university id")
    public ResponseEntity<StudentReturnDto> getById(@PathVariable(name = "universityId") int id){
        return new ResponseEntity<>(this.studentService.getStudentByUniveristyId(id),HttpStatus.OK);
    }

    @PostMapping("register")
    @Operation(summary = "Register a student")
    public ResponseEntity<StudentReturnDto> registerStudent(@Valid @RequestBody StudentDto studentDto){
        return new ResponseEntity<>(this.studentService.registerStudent(studentDto), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    @Operation(summary = "Update Student Information")
    public ResponseEntity<StudentReturnDto> updateStudent(@Valid @RequestBody StudentDto studentDto){
        return new ResponseEntity<>(this.studentService.updateStudent(studentDto), HttpStatus.OK);
    }

    @DeleteMapping("delete/{universityId}")
    @Operation(summary = "Delete student from system")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "universityId") int id){
        return new ResponseEntity<>(this.studentService.deleteStudent(id),HttpStatus.OK);
    }
}
