package com.ferbo.tools.result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ferbo.tools.exception.ValidationException;

public class MessageTest {

    // Construcción valida
    @Test
    public void testMessageConstructionWithTitleAndBody() {
        Message msg = new Message(MessageLevel.SUCCESS, "Registro guardado", "Cliente ID 123");
        assertEquals(MessageLevel.SUCCESS, msg.getLevel());
        assertEquals("Registro guardado", msg.getTitle());
        assertEquals("Cliente ID 123", msg.getBody());
    }

    @Test
    public void testMessageConstructionWithTitleOnly() {
        Message msg = new Message(MessageLevel.INFO, "Proceso inciado", null);
        assertEquals(MessageLevel.INFO, msg.getLevel());
        assertEquals("Proceso inciado", msg.getTitle());
        assertNull(msg.getBody());
    }

    // Validaciones
    @Test(expected = ValidationException.class)
    public void testMessageLevelNullThrowsException() {
        new Message(null, "Titulo", "Detalle");
    }

    @Test(expected = ValidationException.class)
    public void testMessageTitleNullThrowsException() {
        new Message(MessageLevel.WARNING, null, "Detalle");
    }

    @Test(expected = ValidationException.class)
    public void testMessageTitleEmptyThrowsException() {
        new Message(MessageLevel.ERROR, "  ", "Detalle");
    }

    // Equals y hashCode
    @Test
    public void testEqualsAndHashCode() {
        Message msg1 = new Message(MessageLevel.SUCCESS, "Titulo", "Cuerpo");
        Message msg2 = new Message(MessageLevel.SUCCESS, "Titulo", "Cuerpo");
        Message msg3 = new Message(MessageLevel.SUCCESS, "Titulo", null);

        assertTrue(msg1.equals(msg2));
        assertEquals(msg1.hashCode(), msg2.hashCode());

        assertFalse(msg1.equals(msg3));
        assertFalse(msg1.hashCode() == msg3.hashCode());
    }

    // toString
    @Test
    public void testToString() {
        Message msgWithBody = new Message(MessageLevel.SUCCESS, "Titulo", "Detalle");
        assertEquals("[SUCCESS] Titulo: Detalle", msgWithBody.toString());

        Message msgWithoutBody = new Message(MessageLevel.INFO, "Titulo", null);
        assertEquals("[INFO] Titulo", msgWithoutBody.toString());
    }
}
