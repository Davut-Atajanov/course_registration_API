package com.example.course_registration_api.repository;

import com.example.course_registration_api.entity.Courses;
import com.example.course_registration_api.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Students, Long> {

    @Query("SELECT c FROM Students c WHERE c.universityId = :universityId")
    Optional<Students> findByUniversityId(@Param("universityId") int universityId);

}
