package com.example.course_registration_api.service;


import com.example.course_registration_api.payload.AddDropDto;

public interface AddDropService {
    String addStudentToCourse(AddDropDto addDropDto);

    String dropStudentsFromCourse(AddDropDto addDropDto);
}

