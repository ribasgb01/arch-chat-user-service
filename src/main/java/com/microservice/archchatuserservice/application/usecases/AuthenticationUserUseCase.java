package com.microservice.archchatuserservice.application.usecases;

import com.microservice.archchatuserservice.application.exceptions.InvalidCredentialsException;
import com.microservice.archchatuserservice.application.gateways.PasswordEncodeGateway;
import com.microservice.archchatuserservice.application.gateways.TokenProviderGateway;
import com.microservice.archchatuserservice.application.gateways.UserRepositoryGateway;
import com.microservice.archchatuserservice.application.usecases.dto.AuthenticationUserInput;
import com.microservice.archchatuserservice.application.usecases.dto.RegisterUserInput;
import com.microservice.archchatuserservice.domain.User;

public class AuthenticationUserUseCase {

    private final PasswordEncodeGateway passwordEncodeGateway;
    private final UserRepositoryGateway userRepositoryGateway;
    private final TokenProviderGateway tokenProviderGateway;

    public AuthenticationUserUseCase(
            PasswordEncodeGateway passwordEncodeGateway,
            UserRepositoryGateway userRepositoryGateway,
            TokenProviderGateway tokenProviderGateway){
        this.passwordEncodeGateway = passwordEncodeGateway;
        this.userRepositoryGateway = userRepositoryGateway;
        this.tokenProviderGateway = tokenProviderGateway;
    }

    public String authentication(AuthenticationUserInput input){
        User user = userRepositoryGateway.findByEmail(input.email())
                .orElseThrow(() -> new InvalidCredentialsException("E-mail ou senha incorretos."));

        boolean isPasswordValid = passwordEncodeGateway.matches(input.password(), user.getPassword());

        if (!isPasswordValid) {
            throw new InvalidCredentialsException("E-mail ou senha incorretos.");
        }

        return tokenProviderGateway.generateToken(user);
    }
}
