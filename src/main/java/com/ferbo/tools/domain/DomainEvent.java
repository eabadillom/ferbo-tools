package com.ferbo.tools.domain;

import java.time.Instant;

/**
 * Contrato base para eventos de dominio.
 * 
 * <p>
 * Un DomainEvent representa algo que ya ocurrió en el dominio.
 * Es inmutable y describe un hecho del pasado.
 * </p>
 * 
 * <p>
 * No contiene comportamiento, solo datos relevantes del evento.
 * </p>
 */
public interface DomainEvent {

    /**
     * Momento en que ocurrió el evento.
     * 
     * @return timestamp del evento
     */
    Instant occurredAt();
}
