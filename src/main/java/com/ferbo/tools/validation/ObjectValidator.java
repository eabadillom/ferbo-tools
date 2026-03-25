package com.ferbo.tools.validation;

/**
 * Utilidad para validar objetos en general.
 * 
 * <p>
 * Proporciona validaciones básica aplicables a cualquier tipo
 * de objeto, principalmente la verificación de nulidad.
 * </p>
 */
public final class ObjectValidator {

    private ObjectValidator() {
        // Evita instanciación
    }

    /**
     * Verifica que el objeto no se nulo. 
     * 
     * @param value objeto a validar
     * @param field nombre del campo
     * @param notification contendor de errores
     */
    public static void notNull(Object value, String field, Notification notification) {
        if (value == null) {
            notification.addError(field + " no debe ser nulo");
        }
    }
}
