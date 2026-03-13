package com.ferbo.tools.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Pruebas unitarias para BussinesException.
 */
public class BussinesExceptionTest {

    @Test
    public void debeCrearExcepcionConMensaje(){
        
        BussinesException ex = new BussinesException("Regla de negocio violada");

        assertEquals("Regla de negocio violada", ex.getMessage());
    }

    @Test
    public void debeCrearExcepcionConCausa() {
        RuntimeException causa = new RuntimeException("error interno");

        BussinesException ex = new BussinesException("Error de negocio", causa);

        assertEquals("Error de negocio", ex.getMessage());
        assertEquals(causa, ex.getCause());
    }
}
