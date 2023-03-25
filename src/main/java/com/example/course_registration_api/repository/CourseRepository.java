package com.example.course_registration_api.repository;

import com.example.course_registration_api.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Courses, Long> {

    @Query("SELECT c FROM Courses c WHERE c.courseCode = :courseId")
    Optional<Courses> findByCourseId(@Param("courseId") int courseId);

    List<Courses> findByIdIn(List<Long> courseIds);
}
