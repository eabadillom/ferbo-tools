package com.ferbo.tools.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ferbo.tools.result.OperationResult;

public class TextValidatorTest {

    @Test
    public void shouldReturnSuccessForValidText() {
        TextValidator validator = new TextValidator(10);

        OperationResult<String> result = validator.validate("Hola");

        assertTrue(result.isSuccess());
        assertEquals("Hola", result.getData());
    }

    @Test
    public void shouldReturnFailureForNullText() {
        TextValidator validator = new TextValidator(10);

        OperationResult<String> result = validator.validate(null);

        assertFalse(result.isSuccess());
        assertTrue(result.hasErrors());
        assertEquals("El texto no puede ser vacío o nulo", result.getMessages().get(0).getBody());
    }

    @Test
    public void shouldReturnFailureForEmptyText() {
        TextValidator validator = new TextValidator(10);

        OperationResult<String> result = validator.validate("   ");

        assertFalse(result.isSuccess());
        assertTrue(result.hasErrors());
    }

    @Test
    public void shouldReturnFailureForTooLongText() {
        TextValidator validator = new TextValidator(5);

        OperationResult<String> result = validator.validate("Excedido");

        assertFalse(result.isSuccess());
        assertTrue(result.hasErrors());
        assertTrue(result.getMessages().get(0).getBody().contains("longitud máxima"));
    }

    @Test
    public void shouldReturnSuccessWithoutMaxLength() {
        TextValidator validator = new TextValidator();

        OperationResult<String> result = validator.validate("Texto largo que supera cualquier límite");

        assertTrue(result.isSuccess());
        assertEquals("Texto largo que supera cualquier límite", result.getData());
    }
}
