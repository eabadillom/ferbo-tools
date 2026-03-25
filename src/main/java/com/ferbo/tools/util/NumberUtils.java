package com.ferbo.tools.util;

import java.math.BigDecimal;

/**
 * Clase utilitaria para operaciones comunes con números.
 * Todas las funciones son estáticas.
 */
public final class NumberUtils {

    // Constructor privado para evitar instanciación
    private NumberUtils() { }

    /**
     * Verifica si un Number es nulo o cero.
     * Compatible con Integer, Double y BigDecimal.
     *
     * @param value Número a evaluar
     * @return true si es null o igual a cero
     */
    public static boolean isNullOrZero(Number value) {
        if (value == null) return true;
        if (value instanceof Integer) return ((Integer) value) == 0;
        if (value instanceof Double) return ((Double) value) == 0.0;
        if (value instanceof BigDecimal) return BigDecimal.ZERO.compareTo((BigDecimal) value) == 0;
        return false; // Otros tipos numéricos no contemplados
    }

    /**
     * Convierte un String a Integer de manera segura.
     *
     * @param str cadena a convertir
     * @param defaultValue valor por defecto si falla la conversión
     * @return Integer convertido o defaultValue
     */
    public static Integer toInteger(String str, Integer defaultValue) {
        if (str == null) return defaultValue;
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Convierte un String a Double de manera segura.
     *
     * @param str cadena a convertir
     * @param defaultValue valor por defecto si falla la conversión
     * @return Double convertido o defaultValue
     */
    public static Double toDouble(String str, Double defaultValue) {
        if (str == null) return defaultValue;
        try {
            return Double.parseDouble(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Convierte un String a BigDecimal de manera segura.
     *
     * @param str cadena a convertir
     * @param defaultValue valor por defecto si falla la conversión
     * @return BigDecimal convertido o defaultValue
     */
    public static BigDecimal toBigDecimal(String str, BigDecimal defaultValue) {
        if (str == null) return defaultValue;
        try {
            return new BigDecimal(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}