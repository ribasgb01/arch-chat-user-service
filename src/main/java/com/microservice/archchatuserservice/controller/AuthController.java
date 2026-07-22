package com.microservice.archchatuserservice.controller;

import com.microservice.archchatuserservice.application.usecases.AuthenticationUserUseCase;
import com.microservice.archchatuserservice.application.usecases.dto.AuthenticationUserInput;
import com.microservice.archchatuserservice.controller.dto.request.LoginRequest;
import com.microservice.archchatuserservice.controller.dto.response.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public final AuthenticationUserUseCase authenticationUserUseCase;

    public AuthController(AuthenticationUserUseCase authenticationUserUseCase){
        this.authenticationUserUseCase = authenticationUserUseCase;
    }

    @RequestMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        AuthenticationUserInput input = new AuthenticationUserInput(
                request.email(),
                request.password()
        );

        String token = authenticationUserUseCase.authentication(input);

        LoginResponse response = new LoginResponse(token);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
