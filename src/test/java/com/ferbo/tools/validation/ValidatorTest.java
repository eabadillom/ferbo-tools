package com.ferbo.tools.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Prueba básica para el contrato de validator.
 */
public class ValidatorTest {

    private static class DummyValidator implements Validator<String>{

        @Override
        public void validate(String value, Notification notification) {
            if (value == null || value.isEmpty()) {
                notification.addError("valor vacío");
            }
        }
    }

    @Test
    public void debeDetectarValorInvalido() {
        Validator<String> validator = new DummyValidator();

        Notification notification = new Notification();

        validator.validate("", notification);

        assertTrue(notification.hasErrors());
        assertEquals(1, notification.getErrors().size());
    }
}
