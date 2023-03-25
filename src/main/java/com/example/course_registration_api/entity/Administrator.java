package com.example.course_registration_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "administrators"

)
public class Administrator {
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
    private String hash;

    @Column(unique = true)
    private int universityId;
}
