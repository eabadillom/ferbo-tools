package com.ferbo.tools.validation;

/**
 * Validador de números enteros.
 *
 * <p>
 * Permite validar un valor dentro de un rango opcional:
 * - valor mínimo
 * - valor máximo
 * </p>
 */
public class IntegerValidator implements Validator<Integer> {

    private final Integer min;
    private final Integer max;

    /**
     * Constructor con rango opcional.
     *
     * @param min valor mínimo permitido (nullable)
     * @param max valor máximo permitido (nullable)
     */
    public IntegerValidator(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Constructor sin rango
     */
    public IntegerValidator() {
        this(null, null);
    }

    @Override
    public void validate(Integer target, Notification notification) {

        // Validación de nulo
        if (target == null) {
            notification.addError("El valor no puede ser nulo");
            return;
        }

        // Validación de mínimo
        if (min != null && target < min) {
            notification.addError("El valor no puede ser menor que " + min);
        }

        // Validación de máximo
        if (max != null && target > max) {
            notification.addError("El valor no puede ser mayor que " + max);
        }
    }
}