package com.ferbo.tools.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class NumberUtilsTest {

    @Test
    public void testIsNullOrZero() {
        // null
        assertTrue(NumberUtils.isNullOrZero((Integer) null));
        assertTrue(NumberUtils.isNullOrZero((Double) null));
        assertTrue(NumberUtils.isNullOrZero((BigDecimal) null));

        // cero
        assertTrue(NumberUtils.isNullOrZero(0));
        assertTrue(NumberUtils.isNullOrZero(0.0));
        assertTrue(NumberUtils.isNullOrZero(BigDecimal.ZERO));

        // valores no cero
        assertFalse(NumberUtils.isNullOrZero(5));
        assertFalse(NumberUtils.isNullOrZero(3.14));
        assertFalse(NumberUtils.isNullOrZero(new BigDecimal("2.5")));
    }

    @Test
    public void testToInteger() {
        assertEquals(Integer.valueOf(5), NumberUtils.toInteger("5", 0));
        assertEquals(Integer.valueOf(0), NumberUtils.toInteger(null, 0));
        assertEquals(Integer.valueOf(0), NumberUtils.toInteger("abc", 0));
    }

    @Test
    public void testToDouble() {
        assertEquals(Double.valueOf(2.5), NumberUtils.toDouble("2.5", 0.0));
        assertEquals(Double.valueOf(0.0), NumberUtils.toDouble(null, 0.0));
        assertEquals(Double.valueOf(0.0), NumberUtils.toDouble("xyz", 0.0));
    }

    @Test
    public void testToBigDecimal() {
        assertEquals(new BigDecimal("3.14"), NumberUtils.toBigDecimal("3.14", BigDecimal.ZERO));
        assertEquals(BigDecimal.ZERO, NumberUtils.toBigDecimal(null, BigDecimal.ZERO));
        assertEquals(BigDecimal.ZERO, NumberUtils.toBigDecimal("abc", BigDecimal.ZERO));
    }
}