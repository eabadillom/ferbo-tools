package com.ferbo.tools.exception;

/**
 * Excepción que indica violaciones de reglas de negocio complejas.
 * 
 * <p>
 * Se utiliza cuando una operación no puede completarse debido a 
 * condiciones de negocio más elaboradas que combinan múltiples factores.
 * </p>
 * 
 * <p>
 * Esta excepción permite centralizar y diferenciar errores de reglas
 * complejas de los errores simples de negocio (BussinesException).
 * </p>
 */
public class RuleException extends BusinessException {

    public RuleException(String message) {
        super(message);
    }

    public RuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleException(String code, String message) {
        super(code, message);
    }

    public RuleException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
