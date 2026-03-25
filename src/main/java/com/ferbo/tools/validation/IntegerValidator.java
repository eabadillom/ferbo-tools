package com.ferbo.tools.validation;

import com.ferbo.tools.result.MessageLevel;
import com.ferbo.tools.result.OperationResult;
import com.ferbo.tools.result.ResultBuilder;

/**
 * Validador de números enteros.
 *
 * <p>
 * Permite validar un valor dentro de un rango opcional:
 * - valor mínimo
 * - valor máximo
 * </p>
 *
 * @param <T> tipo de dato a validar (Integer)
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
    public OperationResult<Integer> validate(Integer target) {
        ResultBuilder<Integer> builder = ResultBuilder.<Integer>success().data(target);

        if (target == null) {
            builder = ResultBuilder.<Integer>failure()
                    .message(MessageLevel.ERROR, "Valor inválido", "El valor no puede ser nulo");
        } else {
            if (min != null && target < min) {
                builder = ResultBuilder.<Integer>failure()
                        .message(MessageLevel.ERROR, "Valor inválido", "El valor no puede ser menor que " + min);
            } else if (max != null && target > max) {
                builder = ResultBuilder.<Integer>failure()
                        .message(MessageLevel.ERROR, "Valor inválido", "El valor no puede ser mayor que " + max);
            }
        }

        return builder.build();
    }
}
