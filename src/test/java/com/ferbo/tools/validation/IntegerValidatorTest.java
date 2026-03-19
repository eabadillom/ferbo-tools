package com.ferbo.tools.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ferbo.tools.result.OperationResult;

public class IntegerValidatorTest {

    @Test
    public void shouldReturnSuccessForValidValue() {
        IntegerValidator validator = new IntegerValidator(0, 10);

        OperationResult<Integer> result = validator.validate(5);

        assertTrue(result.isSuccess());
        assertEquals(Integer.valueOf(5), result.getData());
    }

    @Test
    public void shouldReturnFailureForNullValue() {
        IntegerValidator validator = new IntegerValidator(0, 10);

        OperationResult<Integer> result = validator.validate(null);

        assertFalse(result.isSuccess());
        assertTrue(result.hasErrors());
        assertEquals("El valor no puede ser nulo", result.getMessages().get(0).getBody());
    }

    @Test
    public void shouldReturnFailureForBelowMin() {
        IntegerValidator validator = new IntegerValidator(1, 10);

        OperationResult<Integer> result = validator.validate(0);

        assertFalse(result.isSuccess());
        assertTrue(result.hasErrors());
        assertTrue(result.getMessages().get(0).getBody().contains("menor que"));
    }

    @Test
    public void shouldReturnFailureForAboveMax() {
        IntegerValidator validator = new IntegerValidator(0, 10);

        OperationResult<Integer> result = validator.validate(15);

        assertFalse(result.isSuccess());
        assertTrue(result.hasErrors());
        assertTrue(result.getMessages().get(0).getBody().contains("mayor que"));
    }

    @Test
    public void shouldReturnSuccessWithoutRange() {
        IntegerValidator validator = new IntegerValidator();

        OperationResult<Integer> result = validator.validate(1000);

        assertTrue(result.isSuccess());
        assertEquals(Integer.valueOf(1000), result.getData());
    }
}