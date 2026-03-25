package com.ferbo.tools.functional;

import com.ferbo.tools.exception.ToolException;

/**
 * Representa un Consummer que puede lanzar una excepción.
 * 
 * @param <T> tipo de entrada 
 */
@FunctionalInterface
public interface ThrowingConsumer<T>{

    void accept(T t) throws Exception;

    static <T> java.util.function.Consumer<T> wrap(ThrowingConsumer<T> consumer) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Exception e) {
                throw new ToolException("Error en ThrowingConsumer", e);
            }
        };
    }
}
