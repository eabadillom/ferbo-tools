package com.ferbo.tools.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ferbo.tools.result.MessageLevel;
import com.ferbo.tools.result.OperationResult;
import com.ferbo.tools.result.ResultBuilder;

/**
 * Prueba básica para el contrato de validator.
 */
public class ValidatorTest {

    private static class DummyValidator implements Validator<String> {

        @Override
        public OperationResult<String> validate(String target) {

            if (target == null || target.trim().isEmpty()) {
                return ResultBuilder.<String>failure()
                        .message(MessageLevel.ERROR, "Error", "Texto vacío")
                        .build();
            }

            return ResultBuilder.<String>success()
                    .data(target)
                    .build();
        }
    }

    @Test
    public void shouldReturnSuccessWhenValid() {

        Validator<String> validator = new DummyValidator();

        OperationResult<String> result = validator.validate("Hola");

        assertTrue(result.isSuccess());
        assertEquals("Hola", result.getData());
    }

    @Test
    public void shouldReturnFailureWhenInvalid() {

        Validator<String> validator = new DummyValidator();

        OperationResult<String> result = validator.validate("");

        assertFalse(result.isSuccess());
        assertTrue(result.hasErrors());
        assertNull(result.getData());
    }
}
