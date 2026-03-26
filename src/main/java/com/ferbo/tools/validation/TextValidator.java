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
 */
public class TextValidator implements Validator<String> {

    private final int maxLength;

    /**
     * Constructor con longitud máxima permitida.
     *
     * @param maxLength longitud máxima del texto (si <= 0 no se valida)
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
    public void validate(String target, Notification notification) {

        // Validación de nulo o vacío
        if (target == null || target.trim().isEmpty()) {
            notification.addError("El texto no puede ser vacío o nulo");
            return;
        }

        // Validación de longitud máxima
        if (maxLength > 0 && target.length() > maxLength) {
            notification.addError(
                "El texto excede la longitud máxima de " + maxLength + " caracteres"
            );
        }
    }
}