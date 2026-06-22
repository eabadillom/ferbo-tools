package com.ferbo.tools.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IntegerValidatorTest {

    private Notification notification;

    @Test
    public void shouldReturnSuccessForValidValue() {
        IntegerValidator validator = new IntegerValidator(0, 10);
        notification = new Notification();

        validator.validate(5, notification);

        assertFalse(notification.hasErrors());
    }

    @Test
    public void shouldReturnFailureForNullValue() {
        IntegerValidator validator = new IntegerValidator(0, 10);
        notification = new Notification();

        validator.validate(null, notification);

        assertTrue(notification.hasErrors());
        assertEquals("El valor no puede ser nulo", notification.getErrors().get(0));
    }

    @Test
    public void shouldReturnFailureForBelowMin() {
        int min = 1;
        IntegerValidator validator = new IntegerValidator(min, 10);
        notification = new Notification();

        validator.validate(0, notification);

        assertTrue(notification.hasErrors());
        assertTrue(notification.getErrors().get(0).contains("El valor no puede ser menor que " + min));
    }

    @Test
    public void shouldReturnFailureForAboveMax() {
        int max = 10;
        IntegerValidator validator = new IntegerValidator(0, max);
        notification = new Notification();

        validator.validate(15, notification);

        assertTrue(notification.hasErrors());
        assertTrue(notification.getErrors().get(0).contains("El valor no puede ser mayor que " + max));
    }

    @Test
    public void shouldReturnSuccessWithoutRange() {
        IntegerValidator validator = new IntegerValidator();
        notification = new Notification();

        validator.validate(1000, notification);

        assertFalse(notification.hasErrors());
    }
}