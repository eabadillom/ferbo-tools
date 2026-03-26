package com.ferbo.tools.validation;

/**
 * Punto de entrada para validación de objetos complejos.
 */
public final class Validation {

    private Validation() {}

    public static ObjectValidatorBuilder objeto(Object target) {
        return new ObjectValidatorBuilder(target);
    }
}