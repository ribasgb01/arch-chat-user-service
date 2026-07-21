package com.microservice.archchatuserservice.infrastructure.config;

import com.microservice.archchatuserservice.application.gateways.PasswordEncodeGateway;
import com.microservice.archchatuserservice.application.gateways.UserRepositoryGateway;
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
}
