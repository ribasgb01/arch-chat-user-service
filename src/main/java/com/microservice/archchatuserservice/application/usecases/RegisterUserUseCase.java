package com.microservice.archchatuserservice.application.usecases;

import com.microservice.archchatuserservice.application.exceptions.EmailAlreadyInUseException;
import com.microservice.archchatuserservice.application.exceptions.MinimumAgeException;
import com.microservice.archchatuserservice.application.exceptions.NicknameAlreadyInUseException;
import com.microservice.archchatuserservice.application.gateways.PasswordEncodeGateway;
import com.microservice.archchatuserservice.application.gateways.UserRepositoryGateway;
import com.microservice.archchatuserservice.application.usecases.dto.RegisterUserInput;
import com.microservice.archchatuserservice.domain.Role;
import com.microservice.archchatuserservice.domain.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class RegisterUserUseCase {

    private final UserRepositoryGateway userRepositoryGateway;
    private final PasswordEncodeGateway passwordEncodeGateway;

    public RegisterUserUseCase(UserRepositoryGateway userRepositoryGateway, PasswordEncodeGateway passwordEncodeGateway){

        this.userRepositoryGateway = userRepositoryGateway;
        this.passwordEncodeGateway = passwordEncodeGateway;
    }

    public User register(RegisterUserInput input){

        int age = Period.between(input.birthDate(), LocalDate.now()).getYears();

        if (age < 18){
            throw new MinimumAgeException("Você precisa ter pelo menos 18 anos para se cadastrar");
        }

        Optional<User> user = userRepositoryGateway.findByEmail(input.email());

        if (user.isPresent()){
            throw new EmailAlreadyInUseException("E-mail já está em uso.");
        }

        user = userRepositoryGateway.findByNickname(input.nickname());

        if (user.isPresent()){
            throw new NicknameAlreadyInUseException("Nickname já está em uso.");
        }

        String hashedPassword = passwordEncodeGateway.encode(input.password());

        User newUser = User.builder()
                .email(input.email())
                .password(hashedPassword)
                .username(input.username())
                .nickname(input.nickname())
                .birthDate(input.birthDate())
                .isEmailVerified(false)
                .role(Role.USER)
                .build();

        return userRepositoryGateway.save(newUser);
    }
}
