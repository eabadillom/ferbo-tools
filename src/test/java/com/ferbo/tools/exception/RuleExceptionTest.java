package com.ferbo.tools.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Pruebas unitarias para RuleException
 */
public class RuleExceptionTest {

    @Test
    public void debeCrearExcecpcionConMensaje(){
        RuleException ex = new RuleException("Regla compleja violada");
        assertEquals("Regla compleja violada", ex.getMessage());
    }

    @Test
    public void debeCrearExcepcionConCausa() {
        RuntimeException causa = new RuntimeException("error interno");
        RuleException ex = new RuleException("Regla compleja fallida", causa);

        assertEquals("Regla compleja fallida", ex.getMessage());
        assertEquals(causa, ex.getCause());
    }
}
