package com.example.ecommerce.user.usecase;

import com.example.ecommerce.user.domain.User;

/**
 * Caso de uso para registrar um novo usuário.
 * Regra esperada (será coberta por testes TDD):
 * - Email não pode existir
 * - Senha >= 8 caracteres
 * - Retornar entidade criada
 */
public interface UserRegistrationUseCase {
    User register(String name, String email, String rawPassword);
}
