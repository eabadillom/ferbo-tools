package com.ferbo.tools.validation;

/**
 * Utilidad para validar valores de tipo Integer.
 * 
 * <p>
 * Proporciona métodos estáticos para validar números enteros
 * en diferentes escenarios comunes dentro de sistemas empresariales.
 * </p>
 */
public final class IntegerValidator {

    private IntegerValidator() {
        // Evita instanciación
    }

    /**
     * Verifica que el valor no sea nulo.
     * 
     * @param value valor a valdar
     * @param field nombre del campo
     * @param notification contenedor de errores
     */
    public static void notNull(Integer value, String field, Notification notification) {
        if (value == null) {
            notification.addError(field + " no debe ser nulo");
        }
    }

    /**
     * Verificar que el valor sea positivo (> 0)
     * 
     * @param value valor a validar
     * @param field nombre del campo
     * @param notification contenedor de errores
     */
    public static void positive(Integer value, String field, Notification notification) {
        if (value != null && value <= 0) {
            notification.addError(field + " debe ser mayor que cero");
        }
    }

    /**
     * Verificar que el valor sea mayor o igual que un minimo.
     * 
     * @param value valor a validar
     * @param min valor minimo permitido
     * @param field nombre del campo
     * @param notification contenedor de errores
     */
    public static void min(Integer value, int min, String field, Notification notification) {
        if (value != null && value < min) {
            notification.addError(field + "debe ser mayor o igual que " + min );
        }
    }

    /**
     * Verifica que el valor esté dentro de un rango
     * 
     * @param value valor a validar
     * @param min minimo permitido
     * @param max máximo permitido
     * @param field nombre del campo
     * @param notification contenedor de errores
     */
    public static void range(Integer value, int min, int max, String field, Notification notification) {
        if (value != null && value < min || value > max) {
            notification.addError(field + " debe estar entre " + min + " y " + max);
        }
    }
}
