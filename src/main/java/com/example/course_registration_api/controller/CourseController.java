package com.example.course_registration_api.controller;


import com.example.course_registration_api.payload.course.CourseDto;
import com.example.course_registration_api.payload.course.CourseReturnDto;
import com.example.course_registration_api.payload.student.StudentReturnDto;
import com.example.course_registration_api.service.CourseService;
import com.example.course_registration_api.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/courses")
@Tag(name = "Course Controller", description = "Operations related to courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }


    @GetMapping("getAll")
    @Operation(summary = "Get all courses")
    public ResponseEntity<List<CourseReturnDto>> getAllStudents(){
        return new ResponseEntity<>(this.courseService.getAllCourses(),HttpStatus.OK);
    }

    @GetMapping("getAllStudents/{courseCode}")
    @Operation(summary = "Get all students in a course")
    public ResponseEntity<List<Map<String, Object>>> getAllStudentsInCourse(@PathVariable(name = "courseCode") int courseCode){
        return new ResponseEntity<>(this.courseService.getAllStudentsInCourse(courseCode),HttpStatus.OK);
    }

    @GetMapping("get/{courseCode}")
    @Operation(summary = "Get course by courseId")
    public ResponseEntity<CourseReturnDto> getByCourseId(@PathVariable(name = "courseCode") int courseCode){
        return new ResponseEntity<>(this.courseService.getCourseByCourseID(courseCode), HttpStatus.OK);
    }

    @PostMapping("create_course")
    @Operation(summary = "Create a new course")
    public ResponseEntity<CourseReturnDto> createCourse(@Valid @RequestBody CourseReturnDto courseReturnDto){
        return new ResponseEntity<>(this.courseService.registerCourse(courseReturnDto), HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{courseCode}")
    @Operation(summary = "Delete course by courseId")
    public ResponseEntity<String> deleteCourse(@PathVariable(name = "courseCode") int courseCode){
        return new ResponseEntity<>(this.courseService.deleteCourse(courseCode), HttpStatus.OK);
    }

    @PutMapping("update")
    @Operation(summary = "Update course by courseId")
    public ResponseEntity<CourseReturnDto> updateCourse(@Valid @RequestBody CourseReturnDto courseReturnDto){
        return new ResponseEntity<>(this.courseService.updateCourse(courseReturnDto), HttpStatus.OK);
    }
}
