package com.ferbo.tools.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Prueba básica para el contrato de validator.
 */
public class ValidatorTest {

    private Notification notification;

    private static class DummyValidator implements Validator<String> {

        private int maxLength;

        public DummyValidator(int maxLength){
            this.maxLength = maxLength;
        }

        public DummyValidator() {
            this(0);
        }

        @Override
        public void validate(String target, Notification notification) {

            if (target == null || target.trim().isEmpty()) {
                notification.addError("El texto no puede ser vacío o nulo");
                return;
            }

            if (maxLength > 0 && target.length() > maxLength) {
                notification.addError(
                        "El texto excede la longitud máxima de " + maxLength + " caracteres");
            }
        }
    }

    @Test
    public void shouldReturnSuccessWhenValid() {
        int max = 10;
        Validator<String> validator = new DummyValidator(max);
        notification = new Notification();

        validator.validate("Hola", notification);

        assertFalse(notification.hasErrors());
    }

    @Test
    public void shouldReturnFailureWhenInvalid() {

        Validator<String> validator = new DummyValidator();
        notification = new Notification();

        validator.validate("", notification);

        assertTrue(notification.hasErrors());
        assertEquals("El texto no puede ser vacío o nulo", notification.getErrors().get(0));
    }
}
