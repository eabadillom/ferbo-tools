package com.ferbo.tools.value.money;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;

import com.ferbo.tools.exception.ValidationException;

/**
 * Pruebas unitarias para la clase CurrencyUtils.
 */
public class CurrencyUtilsTest {

    @Test
    void testGetScale() {
        assertEquals(2, CurrencyUtils.getScale(Currency.getInstance("USD")));
        assertEquals(0, CurrencyUtils.getScale(Currency.getInstance("JPY")));
    }

    @Test
    void testRound() {
        BigDecimal value = new BigDecimal("100.556");
        BigDecimal rounded = CurrencyUtils.round(value, Currency.getInstance("USD"));
        assertEquals(new BigDecimal("100.56"), rounded);
    }

    @Test
    void testNullThrows() {
        assertThrows(ValidationException.class, () -> CurrencyUtils.getScale(null));
        assertThrows(ValidationException.class, () -> CurrencyUtils.round(null, Currency.getInstance("USD")));
    }
}
