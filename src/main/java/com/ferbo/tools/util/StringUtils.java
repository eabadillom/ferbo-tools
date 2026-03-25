package com.ferbo.tools.util;

/**
 * Clase utilitaria para operaciones comunes con cadenas.
 * Todas las funciones son estáticas.
 */
public final class StringUtils {

    // Constructor privado para evitar instanciación
    private StringUtils() { }

    /**
     * Verifica si una cadena es nula o está vacía ("").
     *
     * @param str cadena a verificar
     * @return true si es null o ""
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Verifica si una cadena es nula, vacía o solo espacios en blanco.
     *
     * @param str cadena a verificar
     * @return true si es null, "", o solo espacios
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Retorna una cadena segura, reemplazando null por cadena vacía.
     *
     * @param str cadena a evaluar
     * @return str si no es null, "" en caso contrario
     */
    public static String safe(String str) {
        return str == null ? "" : str;
    }

    /**
     * Compara dos cadenas de manera segura (null safe).
     *
     * @param a primera cadena
     * @param b segunda cadena
     * @return true si ambas son iguales o ambas null
     */
    public static boolean equals(String a, String b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }

    /**
     * Compara dos cadenas ignorando mayúsculas/minúsculas (null safe).
     *
     * @param a primera cadena
     * @param b segunda cadena
     * @return true si ambas son iguales ignorando case
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equalsIgnoreCase(b);
    }
}