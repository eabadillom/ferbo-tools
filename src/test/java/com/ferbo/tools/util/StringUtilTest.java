package com.ferbo.tools.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilTest {

    @Test
    public void testIsEmpty() {
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty(" "));
        assertFalse(StringUtils.isEmpty("Hola"));
    }

    @Test
    public void testIsBlank() {
        assertTrue(StringUtils.isBlank(null));
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank("   "));
        assertFalse(StringUtils.isBlank("Hola"));
    }

    @Test
    public void testSafe() {
        assertEquals("", StringUtils.safe(null));
        assertEquals("Hola", StringUtils.safe("Hola"));
    }

    @Test
    public void testEquals() {
        assertTrue(StringUtils.equals(null, null));
        assertFalse(StringUtils.equals(null, "X"));
        assertFalse(StringUtils.equals("X", null));
        assertTrue(StringUtils.equals("Test", "Test"));
        assertFalse(StringUtils.equals("Test", "test"));
    }

    @Test
    public void testEqualsIgnoreCase() {
        assertTrue(StringUtils.equalsIgnoreCase(null, null));
        assertFalse(StringUtils.equalsIgnoreCase(null, "X"));
        assertFalse(StringUtils.equalsIgnoreCase("X", null));
        assertTrue(StringUtils.equalsIgnoreCase("Test", "test"));
        assertTrue(StringUtils.equalsIgnoreCase("HELLO", "hello"));
    }

}
