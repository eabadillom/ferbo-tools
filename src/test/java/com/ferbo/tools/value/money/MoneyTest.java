package com.ferbo.tools.value.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;

import com.ferbo.tools.exception.BusinessException;

/**
 * Pruebas unitarias para la clase Money.
 */
public class MoneyTest {

    @Test
    public void testCreationAndGetters() {
        Money m = Money.of("100.50", "USD");
        assertEquals(new BigDecimal("100.50"), m.getAmount());
        assertEquals(Currency.getInstance("USD"), m.getCurrency());
    }

    @Test
    public void testAddSubtract() {
        Money m1 = Money.of("100", "USD");
        Money m2 = Money.of("50", "USD");

        assertEquals(Money.of("150", "USD"), m1.add(m2));
        assertEquals(Money.of("50", "USD"), m1.subtract(m2));
    }

    @Test
    public void testMultiplyDivide() {
        Money m = Money.of("100", "USD");

        assertEquals(Money.of("200", "USD"), m.multiply(BigDecimal.valueOf(2)));
        assertEquals(Money.of("50", "USD"), m.divide(BigDecimal.valueOf(2)));
    }

    @Test
    public void testZeroPositiveNegative() {
        Money zero = Money.of("0", "USD");
        Money positive = Money.of("10", "USD");
        Money negative = Money.of("-5", "USD");

        assertTrue(zero.isZero());
        assertTrue(positive.isPositive());
        assertTrue(negative.isNegative());
    }

    @Test
    public void testNegateAndCompare() {
        Money m = Money.of("100", "USD");
        assertEquals(Money.of("-100", "USD"), m.negate());
        assertTrue(m.compareTo(Money.of("50", "USD")) > 0);
    }

    @Test
    public void testDifferentCurrencyThrows() {
        Money m1 = Money.of("100", "USD");
        Money m2 = Money.of("100", "MXN");
        assertThrows(BusinessException.class, () -> m1.add(m2));
    }

    @Test
    public void testEqualsAndHashCode() {
        Money m1 = Money.of("100.00", "USD");
        Money m2 = Money.of("100.0", "USD");
        assertEquals(m1, m2);
        assertEquals(m1.hashCode(), m2.hashCode());
    }
}