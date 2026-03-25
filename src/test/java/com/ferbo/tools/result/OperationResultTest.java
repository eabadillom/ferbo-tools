package com.ferbo.tools.result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.ferbo.tools.exception.ValidationException;

public class OperationResultTest {

    // Construcción válida
    @Test
    public void testValidConstruction() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(MessageLevel.SUCCESS, "OK", "Operación exitosa"));

        OperationResult<String> result = new OperationResult<>(true, messages, 1, "DATA");

        assertTrue(result.isSuccess());
        assertEquals(1, result.getAffectedCount());
        assertEquals(1, result.getMessages().size());
        assertEquals("DATA", result.getData());
    }

    // Validaciones
    @Test(expected = ValidationException.class)
    public void testMessagesNullThrowsException() {
        new OperationResult<>(true, null, 1, null);
    }

    @Test(expected = ValidationException.class)
    public void testAffectedCountNegativeThrowsException() {
        List<Message> messages = new ArrayList<>();
        new OperationResult<>(true, messages, -1, null);
    }

    // Inmutabilidad de mensajes
    @Test(expected = UnsupportedOperationException.class)
    public void testMessagesAreImmutable() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(MessageLevel.INFO, "Info", null));

        OperationResult<Void> result = new OperationResult<>(true, messages, 1, null);

        // Intento de modificacion (debe fallar)
        result.getMessages().add(new Message(MessageLevel.ERROR, "Error", null));
    }

    // hasErrors()
    @Test
    public void testHasErrors() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(MessageLevel.ERROR, "Error", "Fallo"));

        OperationResult<Void> result = new OperationResult<>(false, messages, 0, null);

        assertTrue(result.hasErrors());
    }

    @Test
    public void testHasErrorsFalse() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(MessageLevel.SUCCESS, "OK", null));

        OperationResult<Void> result = new OperationResult<>(true, messages, 1, null);

        assertFalse(result.hasErrors());
    }

    // hasWarnings
    @Test
    public void testHasWarnings() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(MessageLevel.WARNING, "Advertencia", null));

        OperationResult<Void> result = new OperationResult<>(true, messages, 1, null);

        assertTrue(result.hasWarnings());
    }

    @Test
    public void testHasWarningsFalse() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(MessageLevel.SUCCESS, "OK", null));

        OperationResult<Void> result = new OperationResult<>(true, messages, 1, null);

        assertFalse(result.hasWarnings());
    }

    @Test
    public void testTypeSafety() {
        OperationResult<Integer> result = new OperationResult<>(true, Collections.emptyList(), 1, 100);

        Integer value = result.getData(); // sin cast

        assertEquals(Integer.valueOf(100), value);
    }

    // toString
    @Test
    public void testToString() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(MessageLevel.SUCCESS, "OK", null));

        OperationResult<Void> result = new OperationResult<>(true, messages, 1, null);

        String str = result.toString();

        assertTrue(str.contains("success=true"));
        assertTrue(str.contains("affectedCount=1"));
        assertTrue(str.contains("messages="));
    }
}
