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

    /**
     * Crea una excepción de la regla de negocio con mensaje descriptivo.
     * 
     * @param mensaje descripción del error de regla
     */
    public RuleException(String mensaje) {
        super(mensaje);
    }

    /**
     * Crea una excepción de la regla de negocio con mensaje y causa original
     * 
     * @param message descripción del error
     * @param cause excepción original
     */
    public RuleException(String message, Throwable cause) {
        super(message, cause);
    }

}
