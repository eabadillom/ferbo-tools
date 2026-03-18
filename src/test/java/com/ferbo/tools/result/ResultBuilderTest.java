package com.ferbo.tools.result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ferbo.tools.exception.ValidationException;

public class ResultBuilderTest {

    // Construcción SUCCESS
    @Test
    public void testSuccessBuilder() {
        
        OperationResult result = ResultBuilder.success()
                .message(MessageLevel.SUCCESS, "OK", "Operación exitos")
                .affectedCount(1)
                .data("DATA")
                .build();

        assertTrue(result.isSuccess());
        assertEquals(1, result.getAffectedCount());
        assertEquals(1, result.getMessages().size());
        assertEquals("DATA", result.getData());
    }

    // Construccion FAILURE
    @Test
    public void testFailureBuilder() {

        OperationResult result = ResultBuilder.failure()
                .message(MessageLevel.ERROR, "Error", "Fallo el preceso")
                .affectedCount(0)
                .build();

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.hasErrors());
    }

    // Agrega multiples mensajes
    @Test
    public void testMultipleMessages() {
        
        OperationResult result = ResultBuilder.success()
                .message(MessageLevel.INFO, "Inicio", null)
                .message(MessageLevel.WARNING, "Advertencia", "Dato incompleto")
                .message(MessageLevel.SUCCESS, "Fin", null)
                .build();
        
        assertEquals(3, result.getMessages().size());
        assertTrue(result.hasWarnings());
    }

    // Agregar mensaje como objeto
    @Test 
    public void testMessageObject() {
        
        Message msg = new Message(MessageLevel.SUCCESS, "OK", null);

        OperationResult result = ResultBuilder.success()
                .message(msg)
                .build();
        
        assertEquals(1, result.getMessages().size());
        assertEquals(msg, result.getMessages().get(0));
    }

    // Validación mensaje null
    @Test(expected = ValidationException.class)
    public void testMessageNullThrowsException() {
        
        ResultBuilder.success()
                .message((Message) null);
    }

    // Validación affectedCount negativo
    @Test(expected = ValidationException.class)
    public void testAffectedCountNegativeThrowsException() {

        ResultBuilder.success()
                .affectedCount(-1);
    }

    // Build sin mensaje (válido)
    @Test
    public void testBuildWithoutMessages() {

        OperationResult result = ResultBuilder.success()
                .affectedCount(0)
                .build();
        
        
        assertTrue(result.isSuccess());
        assertEquals(0, result.getMessages().size());
    }

    // Fluidez del builder
    @Test
    public void testFluentApi() {
        
        ResultBuilder builder = ResultBuilder.success();
        
        assertSame(builder, builder.message(MessageLevel.INFO, "Test", null));
        assertSame(builder, builder.affectedCount(1));
        assertSame(builder, builder.data("X"));
    }
}   
