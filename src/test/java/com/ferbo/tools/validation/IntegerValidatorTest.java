package com.ferbo.tools.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IntegerValidatorTest {

    @Test
    public void debeDetectarValorNulo() {
        Notification notification = new Notification();

        IntegerValidator.notNull(null, "edad", notification);

        assertTrue(notification.hasErrors());
    }

    @Test
    public void debeDetectarValorNegativo() {

        Notification notification = new Notification();

        IntegerValidator.positive(-5, "cantidad", notification);

        assertTrue(notification.hasErrors());
    }

    @Test
    public void debeDetectarFueraDeRango() {

        Notification notification = new Notification();

        IntegerValidator.range(200, 1, 100, "valor", notification);

        assertTrue(notification.hasErrors());
    }

    @Test
    public void noDebeGenerarErrorCuandoEsValido() {

        Notification notification = new Notification();

        IntegerValidator.range(50, 1, 100, "valor", notification);

        assertFalse(notification.hasErrors());
    }
}
