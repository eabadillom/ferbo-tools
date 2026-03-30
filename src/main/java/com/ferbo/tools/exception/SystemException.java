package com.ferbo.tools.exception;

/**
 * Excepción que indica un error de infraestructura o fallo inesperado.
 * 
 * <p>
 * Se utiliza para capturar problemas de sistema, como fallos de conexión,
 * errores de lectura/escritura, o cualquier situación inesperada que no
 * pueda clasificarse como error de negocio o validación.
 * </p>
 * 
 * <p>
 * Permite a la capa de aplicación diferenciar errores de infraestructura
 * de errores de negocio.
 * </p>
 */
public class SystemException extends RuntimeException {

    private final String code;

    public SystemException(String message) {
        super(message);
        this.code = null;
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
        this.code = null;
    }

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public SystemException(Throwable cause) {
        super(cause);
        this.code = null;
    }

    public String getCode() {
        return code;
    }
}
