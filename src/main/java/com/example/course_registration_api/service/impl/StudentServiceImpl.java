package com.example.course_registration_api.service.impl;

import com.example.course_registration_api.entity.Courses;
import com.example.course_registration_api.entity.Students;
import com.example.course_registration_api.exception.ResourceNotFoundException;
import com.example.course_registration_api.payload.student.StudentDto;
import com.example.course_registration_api.payload.student.StudentReturnDto;
import com.example.course_registration_api.repository.CourseRepository;
import com.example.course_registration_api.repository.CourseStudentRepository;
import com.example.course_registration_api.repository.StudentRepository;
import com.example.course_registration_api.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private CourseStudentRepository courseStudentRepository;
    private CourseRepository courseRepository;

    private ModelMapper mapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                              CourseStudentRepository courseStudentRepository,
                              CourseRepository courseRepository,
                              ModelMapper modelMapper){

        this.studentRepository = studentRepository;
        this.courseStudentRepository = courseStudentRepository;
        this.courseRepository = courseRepository;
        this.mapper = modelMapper;
    }

    @Override
    public StudentReturnDto registerStudent(StudentDto studentDto){
        Students newStudents = this.studentRepository.save(dtoToEntity(studentDto));

        StudentReturnDto response = mapToDto(newStudents);

        return response;
    }

    @Override
    public List<StudentReturnDto> getAllStudent(){
        List<Students> students = this.studentRepository.findAll();


//        return students.stream()
//                .map(student -> {
//                    StudentReturnDto dto = mapToDto(student);
//                    Set<String> courseNames = student.getCourseStudents().stream()
//                            .map(courseStudent -> courseStudent.getCourse().getName())
//                            .collect(Collectors.toSet());
//                    dto.setCourses(courseNames);
//                    return dto;
//                })
//                .collect(Collectors.toList());

        List<StudentReturnDto> studentDtos = new ArrayList<>();

        for (Students student : students) {
            List<Long> courseIds = this.courseStudentRepository.findByStudentId(student.getId())
                    .stream()
                    .map(courseStudent -> courseStudent.getCourseId().getId())
                    .collect(Collectors.toList());

            List<String> courseNames = courseRepository.findByIdIn(courseIds)
                    .stream()
                    .map(Courses::getName)
                    .collect(Collectors.toList());

            StudentReturnDto dto = mapToDto(student);
            dto.setCourses(courseNames);
            studentDtos.add(dto);
        }

        return studentDtos;
    }

    @Override
    public StudentReturnDto getStudentById(Long id){
        Students students = this.studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));


        return mapToDto(students);
    }

    @Override
    public StudentReturnDto updateStudent(StudentDto studentDto){
        Students students = this.studentRepository.findById(studentDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Post", "id", studentDto.getId()));

        students.setName(studentDto.getName());
        students.setSurname(studentDto.getSurname());

        Students updatedStudents = this.studentRepository.save(students);
        return mapToDto(updatedStudents);

    }

    @Override
    public String deleteStudent(Long id) {
        try {
            Students students = this.studentRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
            this.studentRepository.delete(students);
            return "Successfully Deleted";
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    public StudentReturnDto mapToDto(Students newStudents){
        StudentReturnDto response = mapper.map(newStudents, StudentReturnDto.class);
        return response;
    }

    public Students dtoToEntity(StudentDto studentDto){
        Students students = mapper.map(studentDto, Students.class);
        return students;
    }

}
