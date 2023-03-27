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
        name = "courses"

)
public class Courses {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY

    )
    private Long id;

    @Column
    private String name;

    @Column
    private String department;

    @Column
    private String instructorName;

    @Column(unique = true)
    private int courseCode;

    @Column
    private int sections;

    @OneToMany(mappedBy = "courseId",fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = CourseStudent.class)
    private Set<CourseStudent> courseStudents;

}
