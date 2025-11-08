package com.example.ecommerce.user.usecase;

import com.example.ecommerce.user.domain.User;
import com.example.ecommerce.user.port.out.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de TDD para o caso de uso de registro.
 * Começamos com expectativas de comportamento.
 */
class UserRegistrationUseCaseTest {

    private UserRegistrationUseCase useCase;
    private InMemoryRepo repo;

    @BeforeEach
    void setup() {
        repo = new InMemoryRepo();
        useCase = new UserRegistrationService(repo);
    }

    @Test
    void deveFalharQuandoEmailExistir() {
        repo.emails.add("existe@teste.com");
        assertThrows(IllegalStateException.class, () -> useCase.register("Joao", "existe@teste.com", "password123"));
    }

    @Test
    void deveFalharQuandoSenhaCurta() {
        assertThrows(IllegalArgumentException.class, () -> useCase.register("Joao", "novo@teste.com", "1234567"));
    }

    @Test
    void deveCriarUsuarioComId() {
        User user = useCase.register("Maria", "maria@teste.com", "segura123");
        assertNotNull(user.id());
        assertEquals("maria@teste.com", user.email());
        assertTrue(repo.saved); // garante que salvou
    }

    // Repo em memória para testes sem framework
    static class InMemoryRepo implements UserRepository {
        Set<String> emails = new HashSet<>();
        boolean saved = false;
        Map<UUID, User> store = new HashMap<>();

        @Override
        public boolean existsByEmail(String email) { return emails.contains(email.toLowerCase()); }

        @Override
        public User save(User user) {
            saved = true;
            emails.add(user.email());
            store.put(user.id(), user);
            return user;
        }

        @Override
        public Optional<User> findById(UUID id) { return Optional.ofNullable(store.get(id)); }
    }
}
