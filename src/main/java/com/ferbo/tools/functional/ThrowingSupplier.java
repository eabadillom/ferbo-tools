package com.ferbo.tools.functional;

import com.ferbo.tools.exception.ToolException;

/**
 * Representa un Supplier que puede lanzar una excepción.
 * 
 * @param <T> Tipo de dato que devuelve el Supplier
 */
@FunctionalInterface
public interface ThrowingSupplier<T> {

    /**
     * Obtiene un valor, lanzando una excepción si ocurre algún error.
     * 
     * @return valor del Supplier
     * @throws Exception en caso de fallo
     */
    T get() throws Exception;

    /**
     * Método extático para envolver un ThrowingSupplier en uno normal que convierte excepciones en ToolException.
     *  
     * @param supplier ThrowingSupplier a envolver
     * @param <T> tipo de retorno
     * @return Supplier normal que no lanza checked exceptions
     */
    static <T> java.util.function.Supplier<T> wrap(ThrowingSupplier<T> supplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Exception e) {
                throw new ToolException("Error en ThrowingSupplier", e);
            }
        };
    }
}
