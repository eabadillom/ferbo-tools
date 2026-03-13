package com.ferbo.tools.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Acumula errores de validación de forma centralizada.
 * 
 * <p>
 * Permite agregar múltiples errores y luego consultar si exsiten fallas.
 * Esto es util para validaciónes completas de objetos.
 * </p>
 */
public class Notification {

    private final List<String> errors = new ArrayList<>();

    /**
     * Agrega un error a la lista de notificaciones.
     * 
     * @param error mensaje de error
     */
    public void addError(String error) {
        if (error != null && !error.trim().isEmpty()) {
            errors.add(error);
        }
    }

    /**
     * Devuelve la lista de errores acumulados
     * 
     * @return lista inmutable de errores
     */
    public List<String> getErrors(){
        return Collections.unmodifiableList(errors);
    }

    /**
     * Indica si existen errores.
     * 
     * @return true si hay al menos un error
     */
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    /**
     * Lanza una ValidationException si hay errores
     * 
     * @throws com.ferbo.tools.exception.ValidationException con todos los errores concatenados
     */
    public void throwIfHasErrors() {
        if (hasErrors()) {
            throw new com.ferbo.tools.exception.ValidationException(String.join(";", errors));
        }
    }
}
