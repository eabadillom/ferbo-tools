package com.ferbo.tools.exception;

/**
 * Exception que indica que un objeto o dato no pasó la validación.
 * 
 * <p>
 * Se utiliza en los validadores del toolkit para señalar que algún valor
 * no cumple las reglas definidas, por ejemplo:
 * </p>
 * 
 * <ul>
 *   <li>TextValidator: cadena vacía</li>
 *   <li>IntegerValidator: número fuera de rango</li>
 *   <li>MonetaryValidator: monto negativo</li>
 * </ul>
 * 
 * <p>
 * Permite a la capa de aplicación capturar y notificar errores de forma
 * uniforme.
 * </p>
 */
public class ValidationException extends ToolException {

    /**
     * Crea una excepción de validación con mensaje descriptivo.
     * 
     * @param message descripción del error de validación
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Crea una excepción de validación con mensaje y causa.
     * 
     * @param message descripción del error
     * @param cause excepción original
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
