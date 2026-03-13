package com.ferbo.tools.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TextValidatorTest {

    @Test
    public void debeDetectarTextoVacio() {

        Notification notification = new Notification();

        TextValidator.notBlank("", "nombre", notification);

        assertTrue(notification.hasErrors());
    }

    @Test
    public void debeDetectarLongitudMaxima() {

        Notification notification = new Notification();

        TextValidator.maxLength("abcdef", 5, "codigo", notification);

        assertTrue(notification.hasErrors());
    }

    @Test
    public void noDebeGenerarErrorCuandoEsValido() {

        Notification notification = new Notification();

        TextValidator.notBlank("Juan", "nombre", notification);
        TextValidator.maxLength("Juan", 10, "nombre", notification);

        assertFalse(notification.hasErrors());
    }
}
