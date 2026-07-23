package com.microservice.archchatuserservice.infrastructure.config;

import com.microservice.archchatuserservice.application.gateways.CacheGateway;
import com.microservice.archchatuserservice.application.gateways.PasswordEncodeGateway;
import com.microservice.archchatuserservice.application.gateways.TokenProviderGateway;
import com.microservice.archchatuserservice.application.gateways.UserRepositoryGateway;
import com.microservice.archchatuserservice.application.usecases.AuthenticationUserUseCase;
import com.microservice.archchatuserservice.application.usecases.LogoutUserUseCase;
import com.microservice.archchatuserservice.application.usecases.RefreshTokenUseCase;
import com.microservice.archchatuserservice.application.usecases.RegisterUserUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Value("${api.security.token.refresh-expiration}")
    private Long refreshExpiration;

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
            TokenProviderGateway tokenProviderGateway,
            CacheGateway cacheGateway
    ) {
        return new AuthenticationUserUseCase(passwordEncodeGateway, userRepositoryGateway, tokenProviderGateway, cacheGateway, refreshExpiration);
    }

    @Bean
    public RefreshTokenUseCase refreshTokenUseCase(
            TokenProviderGateway tokenProviderGateway,
            CacheGateway cacheGateway,
            UserRepositoryGateway userRepositoryGateway
    ) {
        return new RefreshTokenUseCase(tokenProviderGateway, cacheGateway, userRepositoryGateway);
    }

    @Bean
    public LogoutUserUseCase logoutUserUseCase(CacheGateway cacheGateway, TokenProviderGateway tokenProviderGateway){
        return new LogoutUserUseCase(cacheGateway, tokenProviderGateway);
    }
}
