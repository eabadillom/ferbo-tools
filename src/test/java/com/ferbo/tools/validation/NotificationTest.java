package com.ferbo.tools.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ferbo.tools.exception.ValidationException;

public class NotificationTest {

    @Test
    public void debeAgregarYVerificarErrores() {
        Notification notification = new Notification();
        assertFalse(notification.hasErrors());

        notification.addError("Campo nombre vacio");
        notification.addError("Edad negativa");

        assertTrue(notification.hasErrors());
        assertEquals(2, notification.getErrors().size());
    }

    @Test(expected = ValidationException.class)
    public void debeLanzarExcepcionSiHayErrores() {
        Notification notification = new Notification();
        notification.addError("Campo obligatorio");

        notification.throwIfHasErrors();
    }

    @Test
    public void noAgregarErroresNulosOVacios() {
        Notification notification = new Notification();
        notification.addError(null);
        notification.addError("");
        notification.addError("   ");

        assertFalse(notification.hasErrors());
    }
}
