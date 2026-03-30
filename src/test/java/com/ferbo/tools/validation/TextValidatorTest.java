package com.ferbo.tools.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TextValidatorTest {

    private Notification notification;

    @Test
    public void shouldReturnSuccessForValidText() {
        TextValidator validator = new TextValidator(10);
        notification = new Notification();

        validator.validate("Hola", notification);

        assertFalse(notification.hasErrors());
    }

    @Test
    public void shouldReturnFailureForNullText() {
        TextValidator validator = new TextValidator(10);
        notification = new Notification();

        validator.validate(null, notification);

        assertTrue(notification.hasErrors());
        assertEquals("El texto no puede ser vacío o nulo", notification.getErrors().get(0));
    }

    @Test
    public void shouldReturnFailureForEmptyText() {
        TextValidator validator = new TextValidator(10);
        notification = new Notification();

        validator.validate("   ", notification);

        
        assertTrue(notification.hasErrors());
        assertEquals("El texto no puede ser vacío o nulo", notification.getErrors().get(0));
    }

    @Test
    public void shouldReturnFailureForTooLongText() {
        int maxLength = 5;
        TextValidator validator = new TextValidator(maxLength);
        notification = new Notification();

        validator.validate("Excedido", notification);

        assertTrue(notification.hasErrors());
        assertTrue(notification.getErrors().get(0).contains("El texto excede la longitud máxima de " + maxLength + " caracteres"));
    }

    @Test
    public void shouldReturnSuccessWithoutMaxLength() {
        TextValidator validator = new TextValidator();
        notification = new Notification();

        validator.validate("Texto largo que supera cualquier límite", notification);

        assertFalse(notification.hasErrors());
    }
}
