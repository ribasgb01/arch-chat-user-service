package com.microservice.archchatuserservice.application.gateways;

import com.microservice.archchatuserservice.domain.User;

public interface TokenProviderGateway {

    String generateToken(User user);
    String validateToken(String token);
}
