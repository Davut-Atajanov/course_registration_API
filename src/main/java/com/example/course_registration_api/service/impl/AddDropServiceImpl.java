package com.example.course_registration_api.service.impl;

import com.example.course_registration_api.entity.CourseStudent;
import com.example.course_registration_api.entity.Courses;
import com.example.course_registration_api.entity.Students;
import com.example.course_registration_api.payload.AddDropDto;
import com.example.course_registration_api.repository.CourseRepository;
import com.example.course_registration_api.repository.CourseStudentRepository;
import com.example.course_registration_api.repository.StudentRepository;
import com.example.course_registration_api.service.AddDropService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddDropServiceImpl implements AddDropService {

    private final CourseStudentRepository courseStudentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public AddDropServiceImpl(CourseStudentRepository courseStudentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.courseStudentRepository = courseStudentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public String addStudentToCourse(AddDropDto addDropDto) {
        List<Integer> studentUniversityids = addDropDto.getStudentUniversityIds();
        if (studentUniversityids.size() == 0) {
            return "No student selected";
        }

        Courses course = this.courseRepository.findByCourseId(addDropDto.getCourseCode())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        System.out.println(studentUniversityids.size());
        System.out.println("Before loop");
        CourseStudent courseStudent;
        for (Integer studentUniversityid : studentUniversityids) {
            Students student = this.studentRepository.findByUniversityId(studentUniversityid)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            // check if there is an existing CourseStudent record with the given student id and course id
            Optional<CourseStudent> existingRecord = this.courseStudentRepository.findByStudentIdAndCourseId(student.getId(), course.getId());
            if (existingRecord.isPresent()) {
                System.out.println("Record already exists for student " + studentUniversityid + " and course " + addDropDto.getCourseCode());
                continue; // skip to the next student
            }

            courseStudent = new CourseStudent();
            courseStudent.setStudentId(student);
            courseStudent.setCourseId(course);
            System.out.println(courseStudent.getStudentId().getId());
            System.out.println(courseStudent.getCourseId().getId());
            this.courseStudentRepository.save(courseStudent);
            System.out.println("End of loop");
        }

        return "Students added to course successfully";
    }

    @Override
    public String dropStudentsFromCourse(AddDropDto addDropDto) {
        List<Integer> studentUniversityids = addDropDto.getStudentUniversityIds();
        if (studentUniversityids.size() == 0) {
            return "No student selected";
        }

        Courses course = this.courseRepository.findByCourseId(addDropDto.getCourseCode())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        for (Integer studentUniversityid : studentUniversityids) {
            Students student = this.studentRepository.findByUniversityId(studentUniversityid)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            // find and delete the CourseStudent record with the matching student id and course id
            Optional<CourseStudent> existingRecord = this.courseStudentRepository.findByStudentIdAndCourseId(student.getId(), course.getId());
            if (existingRecord.isPresent()) {
                this.courseStudentRepository.delete(existingRecord.get());
            }
        }

        return "Students dropped from course successfully";
    }
}
