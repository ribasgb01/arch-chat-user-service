package com.microservice.archchatuserservice.application.usecases.dto;

import com.microservice.archchatuserservice.domain.Role;

import java.time.LocalDate;

public record RegisterUserRequest(
        String email,
        String password,
        String username,
        String nickname,
        LocalDate birthDate
) {}
