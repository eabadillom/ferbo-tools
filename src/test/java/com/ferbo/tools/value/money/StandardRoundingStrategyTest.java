package com.ferbo.tools.value.money;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;

import com.ferbo.tools.exception.ValidationException;

/**
 * Pruebas unitarias para la clase StandardRoundingStrategy.
 */
public class StandardRoundingStrategyTest {

    @Test
    public void testRoundUSD() {
        RoundingStrategy strategy = new StandardRoundingStrategy();
        BigDecimal value = new BigDecimal("100.556");
        BigDecimal rounded = strategy.round(value, Currency.getInstance("USD"));
        assertEquals(new BigDecimal("100.56"), rounded);
    }

    @Test
    void testRoundJPY() {
        RoundingStrategy strategy = new StandardRoundingStrategy();
        BigDecimal value = new BigDecimal("100.9");
        BigDecimal rounded = strategy.round(value, Currency.getInstance("JPY"));
        assertEquals(new BigDecimal("101"), rounded);
    }

    @Test
    void testNullsThrow() {
        RoundingStrategy strategy = new StandardRoundingStrategy();
        assertThrows(ValidationException.class, () -> strategy.round(null, Currency.getInstance("USD")));
        assertThrows(ValidationException.class, () -> strategy.round(BigDecimal.ONE, null));
    }
}