package com.example.course_registration_api.repository;

import com.example.course_registration_api.entity.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {

    @Query("SELECT c FROM CourseStudent c WHERE c.studentId.id = :studentId")
    List<CourseStudent> findByStudentId(Long studentId);

    @Query("SELECT c FROM CourseStudent c WHERE c.courseId.id = :courseId")
    List<CourseStudent> findByCourseId(Long courseId);
}
