package com.ferbo.tools.validation;

import java.math.BigDecimal;

/**
 * Utilidad para validar valores monetarios representados por BigDecimal.
 * 
 * <p>
 * Proporciona validaciones communes para montos financieros como
 * valores nulos, negativos o inferiores a un minimo permitido.
 * </p>
 */
public final class MonetaryValidator {

    private MonetaryValidator() {
        // Evita instanciación
    }

    /**
     * Verifica que el monto no sea nulo.
     * 
     * @param value monto a validar
     * @param field nombre del campo
     * @param notification contenedor de errores
     */
    public static void notNull(BigDecimal value, String field, Notification notification) {
        if (value == null) {
            notification.addError(field + " no debe ser nulo");
        }
    }

    /**
     * Varifica que el monto sea mayor que cero.
     * 
     * @param value monto a validar
     * @param field nombre del campo
     * @param notification contenedor de errores
     */
    public static void positive(BigDecimal value, String field, Notification notification) {
        if (value != null && value.compareTo(BigDecimal.ZERO) <= 0) {
            notification.addError(field + " debe ser mayor que cero");
        }
    }

    /**
     * Verifica que el monto sea mayor o igual que cero.
     * 
     * @param value monto a validar
     * @param field nombre del campo
     * @param notification contenedor de errores
     */
    public static void nonNegative(BigDecimal value, String field, Notification notification) {
        if (value != null && value.compareTo(BigDecimal.ZERO) < 0) {
            notification.addError(field + " no puede ser negativo");
        }
    }

    /**
     * Varifica que el monto sea mayor o igual a un minimo. 
     * 
     * @param value monto a validar
     * @param min valor minimo permitido
     * @param field nombre del campo
     * @param notification contenedor de errores
     */
    public static void min(BigDecimal value, BigDecimal min, String field, Notification notification) {
        if (value != null && value.compareTo(min) < 0) {
            notification.addError(field + " debe ser mayor o igual que " + min);
        }
    } 
}
