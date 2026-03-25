package com.ferbo.tools.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ObjectValidatorTest {

    @Test
    public void debeDetectarObjetoNulo() {

        Notification notification = new Notification();

        ObjectValidator.notNull(null, "cliente", notification);

        assertTrue(notification.hasErrors());
    }

    @Test 
    public void noDebeGenerarErrorCuandoObjetoEsValido() {

        Notification notification = new Notification();

        ObjectValidator.notNull("valor", "campo", notification);

        assertFalse(notification.hasErrors());
    }
}
