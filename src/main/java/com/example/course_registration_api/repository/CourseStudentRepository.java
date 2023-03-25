package com.example.course_registration_api.repository;

import com.example.course_registration_api.entity.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {
    List<CourseStudent> findByStudentId(Long studentId);
}
