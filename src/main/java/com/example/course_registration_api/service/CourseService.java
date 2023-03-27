package com.example.course_registration_api.service;

import com.example.course_registration_api.payload.course.CourseDto;
import com.example.course_registration_api.payload.course.CourseReturnDto;
import com.example.course_registration_api.payload.student.StudentDto;
import com.example.course_registration_api.payload.student.StudentReturnDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {
    CourseReturnDto registerCourse(CourseReturnDto courseReturnDto);

    CourseReturnDto getCourseByCourseID(int id);

    List<CourseReturnDto> getAllCourses();

    CourseReturnDto updateCourse(CourseReturnDto courseReturnDto);

    String deleteCourse(int courseId);
}
