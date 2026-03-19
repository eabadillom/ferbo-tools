package com.ferbo.tools.validation;

import com.ferbo.tools.result.OperationResult;

/**
 * Contrato base para todos los validadores del sistema.
 *
 * <p>
 * Un {@code Validator} se encarga de verificar que un objeto cumpla
 * con reglas de negocio o integridad.
 * </p>
 *
 * <p>
 * El resultado de la validación se devuelve como un {@link OperationResult},
 * permitiendo manejar errores sin lanzar excepciones.
 * </p>
 *
 * @param <T> tipo del objeto a validar
 */
public interface Validator<T> {

    /**
     * Ejecuta la validación sobre el objeto proporcionado.
     *
     * @param target objeto a validar
     * @return resultado de la validación
     */
    OperationResult<T> validate(T target);
}
