package com.ferbo.tools.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ToolExceptionTest {

    @Test
    public void debeCrearExcepcionConMensaje() {
        ToolException ex = new ToolException("error de prueba");

        assertEquals("error de prueba", ex.getMessage());
    }
}
