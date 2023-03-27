package com.example.course_registration_api.controller;


import com.example.course_registration_api.payload.AddDropDto;
import com.example.course_registration_api.service.AddDropService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/add_drop")
@Tag(name = "AddDrop Controller", description = "Add/Drop student from course")
public class AddDropController {

    private final AddDropService addDropService;

    public AddDropController(AddDropService addDropService){
        this.addDropService = addDropService;
    }

    @PostMapping("add")
    public ResponseEntity<String> addStudentsToCourse(@Valid @RequestBody AddDropDto addDropDto){
        return new ResponseEntity<>(this.addDropService.addStudentToCourse(addDropDto), HttpStatus.OK);
    }

    @PostMapping("drop")
    public ResponseEntity<String> dropStudentsFromCourse(@Valid @RequestBody AddDropDto addDropDto){
        return new ResponseEntity<>(this.addDropService.dropStudentsFromCourse(addDropDto), HttpStatus.OK);
    }
}
