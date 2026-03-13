package com.ferbo.tools.exception;

/**
 * Excepción que representa errores relacionados con reglas de negocio.
 * 
 * Se utiliza cuando una operacipon no pouede completarse debido a una
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
public class BussinesException extends ToolException {

    /**
     * Crea una excepcipon de negocio con un mensaje descriptivo.
     * 
     * @param message descripción del error de negocio
     */
    public BussinesException(String message) {
        super(message);
    }

    /**
     * Crea una excepción de negocio con mensaje y causa.
     * 
     * @param mensaje descripción del error
     * @param cause excepción original
     */
    public BussinesException(String message, Throwable cause){
        super(message, cause);
    }
}
