package com.example.ecommerce.common.events;

/**
 * Marker interface para eventos de domínio internos.
 * Cada serviço define seus próprios eventos implementando esta interface.
 * Não coloque lógica aqui para evitar acoplamento.
 */
public interface DomainEvent {
    /**
     * Identificador do tipo de evento (ex: UserRegistered). Usado para roteamento.
     */
    String eventType();
}
