package com.ferbo.tools.functional;

import com.ferbo.tools.exception.ToolException;

/**
 * Representa una función que puede lanzar una excepción.
 * 
 * @param <T> Tipo de entreada
 * @param <R> Tipo de salida
 */
@FunctionalInterface
public interface ThrowingFunction <T, R> {

    R apply(T t) throws Exception;

    static <T, R> java.util.function.Function<T, R> wrap(ThrowingFunction<T, R> func) {
        return t -> {
            try {
                return func.apply(t);
            } catch (Exception e) {
                throw new ToolException("Error en ThrowingFunction", e);
            }
        };
    }
}
