package com.ferbo.tools.validation;

/**
 * Contrato base para todos los validadores del sistema.
 *
 * <p>
 * Un {@code Validator} se encarga de verificar que un objeto cumpla
 * con reglas de negocio o integridad.
 * </p>
 *
 * <p>
 * No lanza excepciones directamente ni construye resultados.
 * En su lugar, agrega errores a un {@link Notification}.
 * </p>
 *
 * @param <T> tipo del objeto a validar
 */
public interface Validator<T> {

    /**
     * Ejecuta la validación sobre el objeto proporcionado.
     *
     * @param target objeto a validar
     * @param notification acumulador de errores
     */
    void validate(T target, Notification notification);
}