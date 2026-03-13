package com.ferbo.tools.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class MonetaryValidatorTest {

    @Test
    public void debeDetectarMontoNulo() {

        Notification notification = new Notification();

        MonetaryValidator.notNull(null, "monto", notification);

        assertTrue(notification.hasErrors());
    }

    @Test
    public void debeDetectarMontoNegativo() {

        Notification notification = new Notification();

        MonetaryValidator.nonNegative(new BigDecimal("-10"),"monto", notification);

        assertTrue(notification.hasErrors());
    }

    @Test
    public void debeDetectarMontoMenorAlMinimo() {

        Notification notification = new Notification();

        MonetaryValidator.min(
            new BigDecimal("5"), 
            new BigDecimal("10"), 
            "monto", notification
        );

        assertTrue(notification.hasErrors());
    }

    @Test public void noDebeGenerarErrorCuandoEsValido() {

        Notification notification = new Notification();

        MonetaryValidator.nonNegative(
            new BigDecimal("100"), 
            "monto", 
            notification
        );

        assertFalse(notification.hasErrors());
    }
}
