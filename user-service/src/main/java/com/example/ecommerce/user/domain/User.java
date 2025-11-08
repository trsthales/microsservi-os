package com.example.ecommerce.user.domain;

import java.util.Objects;
import java.util.UUID;

/**
 * Entidade de domínio Usuario.
 * Comentários focam no porquê das decisões:
 * - Mantemos o domínio sem anotações de framework (arquitetura limpa)
 * - Password não é armazenado em claro (hash fora do escopo agora)
 */
public class User {
    private final UUID id;
    private final String name;
    private final String email;
    private final String passwordHash;

    public User(UUID id, String name, String email, String passwordHash) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.passwordHash = Objects.requireNonNull(passwordHash, "passwordHash");
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("nome vazio");
        return name.trim();
    }

    private String validateEmail(String email) {
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("email invalido");
        return email.toLowerCase();
    }

    public UUID id() { return id; }
    public String name() { return name; }
    public String email() { return email; }
    public String passwordHash() { return passwordHash; }
}
