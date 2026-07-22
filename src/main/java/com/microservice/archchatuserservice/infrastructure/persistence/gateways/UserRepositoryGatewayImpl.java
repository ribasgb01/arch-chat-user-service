package com.microservice.archchatuserservice.infrastructure.persistence.gateways;

import com.microservice.archchatuserservice.application.gateways.UserRepositoryGateway;
import com.microservice.archchatuserservice.domain.User;
import com.microservice.archchatuserservice.infrastructure.persistence.DataUserRepository;
import com.microservice.archchatuserservice.infrastructure.persistence.entities.UserEntity;
import com.microservice.archchatuserservice.infrastructure.persistence.mappers.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryGatewayImpl implements UserRepositoryGateway {

    private final DataUserRepository dataUserRepository;
    private final UserMapper userMapper;

    public UserRepositoryGatewayImpl(DataUserRepository dataUserRepository, UserMapper userMapper){
        this.dataUserRepository = dataUserRepository;
        this.userMapper = userMapper;
    }


    @Override
    public User save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        UserEntity savedEntity = dataUserRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return dataUserRepository.findByEmail(email)
                .map(entity -> userMapper.toDomain(entity));
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return dataUserRepository.findByNickname(nickname)
                .map(entity -> userMapper.toDomain(entity));
    }
}
