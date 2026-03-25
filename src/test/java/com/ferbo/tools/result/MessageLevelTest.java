package com.ferbo.tools.result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test simple para validar MessageLevel.
 */
public class MessageLevelTest {

    @Test
    public void testEnumValues() {
        // Validar que los valores existen
        MessageLevel[] levels = MessageLevel.values();

        assertEquals("Debe haber 4 niveles definidos",4, levels.length);
        assertTrue("Debe contener SUCCESS", java.util.Arrays.asList(levels).contains(MessageLevel.SUCCESS));
        assertTrue("Debe contener INFO", java.util.Arrays.asList(levels).contains(MessageLevel.INFO));
        assertTrue("Debe contener WARNING", java.util.Arrays.asList(levels).contains(MessageLevel.INFO));
        assertTrue("Debe contener ERROR", java.util.Arrays.asList(levels).contains(MessageLevel.ERROR));
    }

    @Test 
    public void testEnumName() {
        // Validar nombres de los enums
        assertEquals("SUCCESS", MessageLevel.SUCCESS.name());
        assertEquals("INFO", MessageLevel.INFO.name());
        assertEquals("WARNING", MessageLevel.WARNING.name());
        assertEquals("ERROR", MessageLevel.ERROR.name());
    }
}
