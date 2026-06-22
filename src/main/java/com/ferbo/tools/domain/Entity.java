package com.ferbo.tools.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase base abstracta para entidades del dominio.
 * 
 * <p>
 * Una entidad se define por su identidad única y no por sus atributos.
 * Dos entidades son consideradas iguales si comparten el mismo identificador.
 * </p>
 * 
 * @param <ID> tipo del identificador único
 */
public abstract class Entity<ID> implements Identifiable<ID>, Serializable {

    /**
     * Identificador único de la entidad.
     */
    protected ID id;

    /**
     * Constructor base. 
     * 
     * @param id identificador único
     */
    protected Entity(ID id) {
        this.id = id;
    }

    @Override
    public ID getId() {
        return id;
    }
    
    /**
     * Dos entidades son iguales si:
     * - Son del mismo tipo
     * - Tienen el mismo identificador
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity<?> entity = (Entity<?>) o;

        return id != null && id.equals(entity.id);
    }

    /**
     * Hash basado únicamente en el identificador.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
