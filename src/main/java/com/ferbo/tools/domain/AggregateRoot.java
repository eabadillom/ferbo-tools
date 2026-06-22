package com.ferbo.tools.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Clase base abstracta para Aggregate Roots.
 * 
 * <p>
 * Un Aggregate Root es la entidad principal dentro de un agregado y es el único 
 * punto de acceso para modificar su estado interno.
 * </p>
 * 
 * <p>
 * Permite registrar eventos de dominio ocurridos durante cambios de estado.
 * </p>
 * 
 * @param <ID> tipo del identificador
 */
public abstract class AggregateRoot<ID> extends Entity<ID> {

     /**
     * Lista de eventos de dominio registrados.
     */
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected AggregateRoot(ID id) {
        super(id);
    }

    /**
     * Registra un evento de dominio.
     *
     * <p>
     * Solo debe ser usado dentro del agregado.
     * </p>
     *
     * @param event evento ocurrido
     */
    protected void registerEvent(DomainEvent event) {
        domainEvents.add(Objects.requireNonNull(event, "event no puede ser null"));
    }

    /**
     * Devuelve los eventos registrados (inmutable).
     */
    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    /**
     * Limpia los eventos (usualmente después de publicarlos).
     */
    public void clearDomainEvents() {
        domainEvents.clear();
    }
}
