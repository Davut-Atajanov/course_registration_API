package com.example.course_registration_api.repository;

import com.example.course_registration_api.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students, Long> {
}
