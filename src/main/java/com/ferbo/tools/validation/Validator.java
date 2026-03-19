package com.ferbo.tools.validation;

import com.ferbo.tools.result.OperationResult;

/**
 * Interfaz base para validadores del toolkit.
 * 
 * <p>
 * Define el contrato que deben seguir todos los validadores.
 * Un validador recibe un objeto y registra los errores encontrados
 * en instancia de {@link Notification}.
 * </p>
 * 
 * @param <T> tipo del objeto a validar
 */
public interface Validator<T> {

    /**
     * Ejecuta la validación sobre el objeto proporcionado.
     * 
     * @param value objeto a validar
     * @param notification contenedor donde se registran los errores
     */
    void validate(T value, Notification notification);
}
