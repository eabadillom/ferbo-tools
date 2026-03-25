package com.ferbo.tools.exception;

/**
    * Excepción base para todas las excepciones del toolkit ferbo-tools.
    * 
    * <p>
    * Permite centralizar el manejo de errores relacionados con las utilidades
    * del toolkit. Todas las excepciones especificadas deben extender de esta clase. 
    * </p>
*/
public class ToolException extends RuntimeException {

    /**
     * Crea una excepción con un mensaje descriptivo.
     * 
     * @param message descripción del error
     */
   public ToolException(String message) {
    super(message);
   }

   /**
    * Crea una excepción con mensaje y causa.
    * 
    * @param message drescripción del error
    * @param cause-excepción original que provocó el problema
    */
   public ToolException(String message, Throwable cause){
    super(message, cause);
   }
}
