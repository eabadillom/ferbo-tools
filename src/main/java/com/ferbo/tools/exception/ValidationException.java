package com.ferbo.tools.exception;

/**
 * Exception que indica que un objeto o dato no pasó la validación.
 * 
 * <p>
 * Se utiliza en los validadores del toolkit para señalar que algún valor
 * no cumple las reglas definidas, además para ser utilizable para validaciones de reglas
 * de negocio simples, por ejemplo:
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
public class ValidationException extends BusinessException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String code, String message) {
        super(code, message);
    }

    public ValidationException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
