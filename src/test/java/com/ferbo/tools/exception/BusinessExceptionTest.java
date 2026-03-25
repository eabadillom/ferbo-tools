package com.ferbo.tools.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Pruebas unitarias para BussinesException.
 */
public class BusinessExceptionTest {

    @Test
    public void debeCrearExcepcionConMensaje(){
        
        BusinessException ex = new BusinessException("Regla de negocio violada");

        assertEquals("Regla de negocio violada", ex.getMessage());
    }

    @Test
    public void debeCrearExcepcionConCausa() {
        RuntimeException causa = new RuntimeException("error interno");

        BusinessException ex = new BusinessException("Error de negocio", causa);

        assertEquals("Error de negocio", ex.getMessage());
        assertEquals(causa, ex.getCause());
    }
}
