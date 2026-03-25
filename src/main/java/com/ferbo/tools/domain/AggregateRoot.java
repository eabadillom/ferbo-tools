package com.ferbo.tools.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ferbo.tools.exception.ValidationException;

/**
 * Clase base abstracta para Aggregate Roots.
 * 
 * <p>
 * Un Aggregate Root es la entidad principal dentro de un agregado y es el único 
 * punto de acceso para modificar su estado interno.
 * </p>
 * 
 * <p>
 * Tambien puede registrar eventos de dominio que ocurren durante cambios de estado.
 * </p>
 * 
 * @param <ID> tipo del identificador registrados.
 */
public abstract class AggregateRoot<ID> extends Entity<ID> {

    /**
     * Lista de eventos de dominio registrados.
     */
    private final List<Object> domainEvents = new ArrayList<>();

    protected AggregateRoot (ID id) {
        super(id);
    }

    /**
     * Registra un evento de dominio.
     * 
     * @param event evento ocurrido
     */
    protected void registerEvent(Object event) {
        if (event == null) {
            throw new ValidationException("El evento no puede ser nulo");
        }
        domainEvents.add(event);
    }

    /**
     * Devuelve una lista inmutable de eventos de dominio. 
     * 
     * @return lista de eventos
     */
    public List<Object> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    /**
     * Limpia los eventos de dominio registrados.
     */
    public void clearDomainEvents() {
        domainEvents.clear();
    }
}
