package com.microservice.archchatuserservice.application.gateways;

import com.microservice.archchatuserservice.domain.User;

import java.util.Optional;

public interface UserRepositoryGateway {

    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
}
