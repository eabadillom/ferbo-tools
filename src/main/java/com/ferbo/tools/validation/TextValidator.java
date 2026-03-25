package com.ferbo.tools.validation;

import com.ferbo.tools.result.MessageLevel;
import com.ferbo.tools.result.OperationResult;
import com.ferbo.tools.result.ResultBuilder;

/**
 * Validador de texto genérico.
 *
 * <p>
 * Revisa reglas básicas sobre cadenas de texto:
 * - no nulo
 * - no vacío
 * - longitud máxima
 * </p>
 *
 * @param <T> tipo de texto a validar (normalmente String)
 */
public class TextValidator implements Validator<String> {

    private final int maxLength;

    /**
     * Constructor con longitud máxima permitida.
     *
     * @param maxLength longitud máxima del texto (si <=0 no se valida)
     */
    public TextValidator(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Constructor por defecto (sin límite de longitud)
     */
    public TextValidator() {
        this(0);
    }

    @Override
    public OperationResult<String> validate(String target) {
        ResultBuilder<String> builder = ResultBuilder.<String>success().data(target);

        if (target == null || target.trim().isEmpty()) {
            builder = ResultBuilder.<String>failure()
                    .message(MessageLevel.ERROR, "Texto inválido", "El texto no puede ser vacío o nulo");
        } else if (maxLength > 0 && target.length() > maxLength) {
            builder = ResultBuilder.<String>failure()
                    .message(MessageLevel.ERROR, "Texto inválido", "El texto excede la longitud máxima de " + maxLength + " caracteres");
        }

        return builder.build();
    }
}