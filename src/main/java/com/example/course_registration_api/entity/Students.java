package com.example.course_registration_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "students"

)
public class Students {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY

    )
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String department;

    @Column
    private float cgpa;

    @Column
    private int year;

    @Column(unique = true)
    private int universityId;

    @Column
    private String password;

    @OneToMany(mappedBy = "studentId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CourseStudent> courseStudents;

}
