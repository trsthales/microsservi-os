package com.example.ecommerce.user.usecase;

import com.example.ecommerce.user.domain.User;
import com.example.ecommerce.user.port.out.UserRepository;

import java.util.UUID;

/**
 * Implementação ainda INCOMPLETA do caso de uso.
 * Lançamos UnsupportedOperationException para manter testes vermelhos inicialmente.
 * Você irá completar a lógica conforme os exercícios.
 */
public class UserRegistrationService implements UserRegistrationUseCase {

    private final UserRepository repository;

    public UserRegistrationService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User register(String name, String email, String rawPassword) {
        // Regras mínimas para passar nos testes TDD:
        // 1) Senha com pelo menos 8 caracteres
        if (rawPassword == null || rawPassword.length() < 8) {
            throw new IllegalArgumentException("senha curta");
        }
        // 2) Email deve ser único
        if (repository.existsByEmail(email)) {
            throw new IllegalStateException("email ja existe");
        }
        // 3) Criar usuário com id novo e "hash" simples (placeholder)
        String fakeHash = "HASH:" + rawPassword; // substitua por um hash real ao integrar segurança
        User user = new User(UUID.randomUUID(), name, email, fakeHash);
        return repository.save(user);
    }
}
