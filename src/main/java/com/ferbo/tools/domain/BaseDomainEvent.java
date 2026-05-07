package com.ferbo.tools.domain;

import java.time.Instant;
import java.util.Objects;

/**
 * Implementación base de DomainEvent.
 * 
 * <p>
 * Proporciona el timestamp de ocurrencia automáticamente.
 * Puede ser extendida por eventos concretos.
 * </p>
 */
public abstract class BaseDomainEvent implements DomainEvent {

    private final Instant occurredAt;

    protected BaseDomainEvent() {
        this.occurredAt = Instant.now();
    }

    protected BaseDomainEvent(Instant occurredAt) {
        this.occurredAt = Objects.requireNonNull(occurredAt);
    }

    @Override
    public Instant occurredAt() {
        return occurredAt;
    }
    
}
