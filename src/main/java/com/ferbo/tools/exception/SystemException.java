package com.ferbo.tools.exception;

/**
 * Excepción que indica un error de infraestructura o fallo inesperado.
 * 
 * <p>
 * Se utiliza para capturar problemas de sistema, como fallos de conexión,
 * errores de lectura/escritura, o cualquier situación inesperada que no
 * pueda clasificars como error de negocio o validación.
 * </p>
 * 
 * <p>
 * Permite a la capa de aplicación diferenciar errores de infraestructura
 * de errores de negocio.
 * </p>
 */
public class SystemException extends ToolException{

    /**
     * Crea una excepción de sistema con mensaje descriptivo.
     * 
     * @param message descripción del error
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * Crea una excepción de sistema con mensaje y causa original.
     * 
     * @param message descripción de error
     * @param cause excepción original
     */
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

}
