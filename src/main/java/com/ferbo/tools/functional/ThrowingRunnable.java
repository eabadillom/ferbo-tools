package com.ferbo.tools.functional;

import com.ferbo.tools.exception.ToolException;

/**
 * Respresenta un Runnable que puede lanzar una excepción.
 */
@FunctionalInterface
public interface ThrowingRunnable {

    /**
     * Ejecuta la acción, lanzando una excepción si ocurre algún error.
     * 
     * @throws Exception en caso de fallo
     */
    void run() throws Exception;

    /**
     * Método estático para envolver un ThrowingRunnable en Runnable normal.
     * 
     * @param runnable ThrowingRunnable a envolver
     * @return Runnable normal que no lanza checked exceptions
     */
    static Runnable wrap(ThrowingRunnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw new ToolException("Error en ThrowingRunnable", e);
            }
        };
    }

}
