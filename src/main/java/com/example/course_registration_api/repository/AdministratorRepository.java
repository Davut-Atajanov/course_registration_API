package com.example.course_registration_api.repository;

import com.example.course_registration_api.entity.Administrator;
import com.example.course_registration_api.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    @Query("SELECT c FROM Administrator c WHERE c.universityId = :universityId AND c.password = :password")
    Optional<Administrator> validateAdministrator(@Param("universityId") int universityId
            , @Param("password") String password);
}
