package com.example.course_registration_api.service;

import com.example.course_registration_api.payload.AdministratorDto;
import com.example.course_registration_api.payload.AuthDto;

public interface AuthService {

    AdministratorDto register(AdministratorDto administratorDto);

    String login(AuthDto authDto);
}
