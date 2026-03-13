package com.ferbo.tools.validation;

/**
 * Utilidad para validar valores de tipo String.
 * 
 * <p>
 * Proporciona métodos estaticos para verificar condiciones comunes
 * en cadenas de texto como nulidad, contenido vacio y longitud.
 * </p>
 */
public final class TextValidator {

    private TextValidator() {
        // Evita instanciación
    }

    /**
     * Verifica que el texto no sea nulo ni vacio.
     * 
     * @param value testo a validar
     * @param field nombre del campo
     * @param notification contenedor de errores
     */
    public static void notBlank(String value, String field, Notification notification) {
        if (value == null || value.trim().isEmpty()) {
            notification.addError(field + " no debe estar vacío");
        }
    }

    /**
     * Verifica que el texto no exceda una longitud máxima.
     * 
     * @param value texto a validar
     * @param max longitud máxima permitida
     * @param field nombre del campo
     * @param notification contendor de errores
     */
    public static void maxLength(String value, int max, String field, Notification notification) {
        if (value != null && value.length() > max) {
            notification.addError(field + " excede la longitud máxima de " + max);
        }
    }

    /**
     * Verifica que ele texto tenga una longitud minima.
     * 
     * @param value texto a validar
     * @param min longitud minima requerida
     * @param field nombre del campo
     * @param notification contenedor de errores
     */
    public static void minLength(String value, int min, String field, Notification notification) {
        if (value != null && value.length() < min) {
            notification.addError(field + " debe tener al menos " + min + " caracteres");
        }
    }
}
