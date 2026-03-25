package com.ferbo.tools.domain;

/**
 * Contrato base para objetos que poseen una identidad única.
 * 
 * <p>
 * Esta interfaz define el comportamiento minimo que debe cumplir cualquier objeto
 * que pueda ser identificado dentro del dominio, como entidades o agregados.
 * </p>
 * 
 * <p>
 * El tipo del identificador es genérico para permitir flexibilidad (UUID, Long, String, etc.).
 * </p>
 * 
 * @param <ID> tipo del identificador único
 */
public interface Identifiable<ID> {

    /**
     * Obtiene el identificador único del objeto.
     * 
     * @return identificador único
     */

    ID getId();
}
