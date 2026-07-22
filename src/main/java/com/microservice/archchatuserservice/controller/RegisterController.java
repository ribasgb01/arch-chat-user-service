package com.microservice.archchatuserservice.controller;

import com.microservice.archchatuserservice.application.usecases.RegisterUserUseCase;
import com.microservice.archchatuserservice.application.usecases.dto.RegisterUserInput;
import com.microservice.archchatuserservice.controller.dto.request.RegisterRequest;
import com.microservice.archchatuserservice.controller.dto.response.RegisterResponse;
import com.microservice.archchatuserservice.domain.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class RegisterController {

    private final RegisterUserUseCase registerUserUseCase;

    public RegisterController(RegisterUserUseCase registerUserUseCase){
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register (@Valid @RequestBody RegisterRequest request){

        RegisterUserInput input = new RegisterUserInput(
                request.email(),
                request.password(),
                request.username(),
                request.nickname(),
                request.birthDate()
        );

        User savedUser = registerUserUseCase.register(input);

        RegisterResponse response = new RegisterResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getUsername(),
                savedUser.getNickname(),
                savedUser.isEmailVerified()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
