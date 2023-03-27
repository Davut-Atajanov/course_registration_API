package com.example.course_registration_api.service.impl;

import com.example.course_registration_api.entity.Administrator;
import com.example.course_registration_api.payload.AdministratorDto;
import com.example.course_registration_api.payload.AuthDto;
import com.example.course_registration_api.repository.AdministratorRepository;
import com.example.course_registration_api.security.JwtUtils;
import com.example.course_registration_api.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {

    private AdministratorRepository administratorRepository;
    private ModelMapper mapper;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(AdministratorRepository administratorRepository, ModelMapper mapper, JwtUtils jwtUtils){
        this.administratorRepository = administratorRepository;
        this.mapper = mapper;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public AdministratorDto register(AdministratorDto administratorDto) {
        Administrator newAdmin = this.administratorRepository.save(dtoToEntity(administratorDto));

        AdministratorDto response = mapToDto(newAdmin);

        return response;
    }

    @Override
    public String login(AuthDto authDto) {
        Optional<Administrator> optionalAdministrator = this.administratorRepository.validateAdministrator(authDto.getUniversityId(), authDto.getPassword());
        if (optionalAdministrator.isPresent()) {
            int universityId = optionalAdministrator.get().getUniversityId();
            String password = optionalAdministrator.get().getPassword();

            // Generate JWT token with universityId and password claims
            String jwtToken = this.jwtUtils.generateJwtToken(universityId, password);

            return jwtToken;
        } else {
            return "Login Failed";
        }

    }

    public AdministratorDto mapToDto(Administrator administrator){
        AdministratorDto response = mapper.map(administrator, AdministratorDto.class);
        return response;
    }

    public Administrator dtoToEntity(AdministratorDto administratorDto){
        Administrator administrator = mapper.map(administratorDto, Administrator.class);
        return administrator;
    }
}
