package com.example.ecommerce.user.port.out;

import com.example.ecommerce.user.domain.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Porta de saída (repositorio) para persistência de usuários.
 * Implementações ficarão no adapter de infraestrutura (ex.: JPA, JDBC, etc.).
 */
public interface UserRepository {
    boolean existsByEmail(String email);
    User save(User user);
    Optional<User> findById(UUID id);
}
