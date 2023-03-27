package com.example.course_registration_api.service.impl;

import com.example.course_registration_api.entity.Courses;
import com.example.course_registration_api.entity.Students;
import com.example.course_registration_api.exception.ResourceNotFoundException;
import com.example.course_registration_api.payload.course.CourseReturnDto;
import com.example.course_registration_api.payload.student.StudentReturnDto;
import com.example.course_registration_api.repository.CourseRepository;
import com.example.course_registration_api.repository.CourseStudentRepository;
import com.example.course_registration_api.repository.StudentRepository;
import com.example.course_registration_api.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private CourseStudentRepository courseStudentRepository;
    private StudentRepository studentRepository;

    private ModelMapper mapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseStudentRepository courseStudentRepository, StudentRepository studentRepository, ModelMapper modelMapper) {

        this.courseRepository = courseRepository;
        this.courseStudentRepository = courseStudentRepository;
        this.studentRepository = studentRepository;
        this.mapper = modelMapper;
    }

    @Override
    public CourseReturnDto registerCourse(CourseReturnDto courseReturnDto) {
        Courses courses = this.courseRepository.save(dtoToEntity(courseReturnDto));

        CourseReturnDto response = mapToDto(courses);

        return response;
    }

    @Override
    public CourseReturnDto getCourseByCourseID(int courseId) {
        Courses courses = this.courseRepository.findByCourseId(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "courseId", courseId));
        CourseReturnDto courseReturnDto = mapToDto(courses);
        return courseReturnDto;
    }

    @Override
    public List<CourseReturnDto> getAllCourses() {
        List<Courses> courses = this.courseRepository.findAll();

        return courses.stream().map(course -> mapToDto(course)).collect(Collectors.toList());
    }

    @Override
    public CourseReturnDto updateCourse(CourseReturnDto courseReturnDto) {
        Courses courses = this.courseRepository.findByCourseId(courseReturnDto.getCourseCode())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "courseId", courseReturnDto.getCourseCode()));

        courses.setInstructorName(courseReturnDto.getInstructorName());
        courses.setSections(courseReturnDto.getSections());
        courses.setName(courseReturnDto.getName());

        this.courseRepository.save(courses);

        return this.mapToDto(courses);
    }

    @Override
    public String deleteCourse(int courseId) {
        try {
            Courses courses = this.courseRepository.findByCourseId(courseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Course", "courseId", courseId));
            this.courseRepository.delete(courses);
            return "Successfully Deleted";
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    @Override
    public List<Map<String, Object>> getAllStudentsInCourse(int courseCode) {
    Courses courses = this.courseRepository.findByCourseId(courseCode)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "courseId", courseCode));

        List<Long> studentIds = this.courseStudentRepository.findByCourseId(courses.getId())
                .stream()
                .map(courseStudent -> courseStudent.getId())
                .collect(Collectors.toList());

        Map<String, Object> studentReturnDtos;
        List<Map<String, Object>> studentReturnDtosList = new ArrayList<>();
        for(Long id : studentIds){
            studentReturnDtos = new HashMap<>();
            StudentReturnDto dto = mapToDtoStudent(this.studentRepository.findById(id));
            studentReturnDtos.put("universityId", dto.getUniversityId());
            studentReturnDtos.put("name", dto.getName());
            studentReturnDtos.put("surname", dto.getSurname());
            studentReturnDtosList.add(studentReturnDtos);
        }

        return studentReturnDtosList;
    }


    public CourseReturnDto mapToDto(Courses courses) {
        CourseReturnDto response = mapper.map(courses, CourseReturnDto.class);
        return response;
    }

    public StudentReturnDto mapToDtoStudent(Optional<Students> students) {
        StudentReturnDto response = mapper.map(students, StudentReturnDto.class);
        return response;
    }

    public Courses dtoToEntity(CourseReturnDto courseReturnDto) {
        Courses courses = mapper.map(courseReturnDto, Courses.class);
        return courses;
    }
}
