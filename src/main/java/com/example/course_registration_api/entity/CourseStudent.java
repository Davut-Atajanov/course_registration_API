package com.example.course_registration_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "course_student")
public class CourseStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Courses courseId;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Students studentId;

}
