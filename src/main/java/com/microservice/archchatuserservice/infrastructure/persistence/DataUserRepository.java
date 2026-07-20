package com.microservice.archchatuserservice.infrastructure.persistence;

import com.microservice.archchatuserservice.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DataUserRepository extends JpaRepository<UserEntity, UUID> {
}
