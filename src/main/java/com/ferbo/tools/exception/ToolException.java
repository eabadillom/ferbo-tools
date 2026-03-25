package com.ferbo.tools.exception;

/**
    * Excepción base para todas las excepciones del toolkit ferbo-tools.
    * 
    * <p>
    * Permite centralizar el manejo de errores relacionados con las utilidades
    * del toolkit. 
    * </p>
*/
public class ToolException extends RuntimeException {

    private final String code;

    public ToolException(String message) {
        super(message);
        this.code = null;
    }

    public ToolException(String message, Throwable cause) {
        super(message, cause);
        this.code = null;
    }

    public ToolException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ToolException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ToolException(Throwable cause) {
        super(cause);
        this.code = null;
    }

    public String getCode() {
        return code;
    }
}