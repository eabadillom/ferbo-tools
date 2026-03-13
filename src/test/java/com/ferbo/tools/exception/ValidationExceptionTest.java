package com.ferbo.tools.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Pruebas unitarias para ValidationException.
 */
public class ValidationExceptionTest {

    @Test
    public void debeCrearExcepcionConMensaje() {
        
        ValidationException ex = new ValidationException("Valor inválido");
        
        assertEquals("Valor inválido", ex.getMessage());
    }

    @Test
    public void debeCrearExcepcionConCausa() {

        RuntimeException causa = new RuntimeException("error interno");

        ValidationException ex = new ValidationException("Error de validación", causa);

        assertEquals("Error de validación", ex.getMessage());
        assertEquals(causa, ex.getCause());
    }
}
