package com.microservice.archchatuserservice.infrastructure.config;

import com.microservice.archchatuserservice.application.gateways.PasswordEncodeGateway;
import com.microservice.archchatuserservice.application.gateways.TokenProviderGateway;
import com.microservice.archchatuserservice.application.gateways.UserRepositoryGateway;
import com.microservice.archchatuserservice.application.usecases.AuthenticationUserUseCase;
import com.microservice.archchatuserservice.application.usecases.RegisterUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase (
            UserRepositoryGateway userRepositoryGateway,
            PasswordEncodeGateway passwordEncodeGateway
    ) {
        return new RegisterUserUseCase(userRepositoryGateway, passwordEncodeGateway);
    }

    @Bean
    public AuthenticationUserUseCase authenticationUserUseCase(
            PasswordEncodeGateway passwordEncodeGateway,
            UserRepositoryGateway userRepositoryGateway,
            TokenProviderGateway tokenProviderGateway
    ) {
        return new AuthenticationUserUseCase(passwordEncodeGateway, userRepositoryGateway, tokenProviderGateway);
    }
}
