package com.example.course_registration_api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class CourseRegistrationApiApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();

    }

    public static void main(String[] args) {
        SpringApplication.run(CourseRegistrationApiApplication.class, args);
    }

}
