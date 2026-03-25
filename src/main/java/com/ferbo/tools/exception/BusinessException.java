package com.ferbo.tools.exception;

/**
 * Excepción que representa errores relacionados con reglas de negocio.
 * 
 * Se utiliza cuando una operacipon no puede completarse debido a una
 * condición del dominio del negocio, por ejemplo:
 * 
 * <p>
 * Saldo insuficiente
 * Operación no permitida
 * Cliente bloqueado
 * </p>
 * 
 * <ul>
 *  <li>Saldo insuficiente</li>
 *  <li>Operación no permitida</li>
 *  <li>Cliente bloqueado</li>
 * </ul>
 * 
 * <p>
 * Este tipo de excepciones suelen ser manejadas por la capa de aplicación
 * para informar al usuario o registrar la situación.
 * </p>
 */
public class BusinessException extends RuntimeException {

    private final String code;

    public BusinessException(String message) {
        super(message);
        this.code = null;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = null;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}