package com.ferbo.tools.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Pruebas unitarias para SystemException
 */
public class SystemExceptionTest {

    @Test
    public void debeCrearExcepcionConMensaje() {
        
        SystemException ex = new SystemException("Fallo de infraestructura");
        assertEquals("Fallo de infraestructura", ex.getMessage());
    }

    @Test
    public void debeCrearExceptionConCausa(){
        RuntimeException causa = new RuntimeException("error interno");
        SystemException ex = new SystemException("Error de sistema", causa);

        assertEquals("Error de sistema", ex.getMessage());
        assertEquals(causa, ex.getCause());
    }
}
