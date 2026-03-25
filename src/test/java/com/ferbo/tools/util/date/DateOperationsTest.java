package com.ferbo.tools.util.date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;

public class DateOperationsTest {

    @Test
    public void testIsBefore() {
        LocalDate today = LocalDate.of(2026, 3, 19);
        LocalDate tomorrow = LocalDate.of(2026, 3, 20);
        assertTrue(DateOperations.isBefore(today, tomorrow));
        assertFalse(DateOperations.isBefore(tomorrow, today));
    }

    @Test
    public void testIsBetween() {
        LocalDate start = LocalDate.of(2026, 3, 15);
        LocalDate end = LocalDate.of(2026, 3, 25);
        LocalDate check = LocalDate.of(2026, 3, 19);
        assertTrue(DateOperations.isBetween(check, start, end));
    }

    @Test
    public void testDaysBetween() {
        LocalDate start = LocalDate.of(2026, 3, 1);
        LocalDate end = LocalDate.of(2026, 3, 19);
        assertEquals(18, DateOperations.daysBetween(start, end));
    }

    @Test
    public void testWeeksBetween() {
        LocalDate start = LocalDate.of(2026, 3, 1);
        LocalDate end = LocalDate.of(2026, 3, 19);
        assertEquals(2, DateOperations.weeksBetween(start, end));
    }

    @Test
    public void testDaysBetweenLegacy() {
        Date start = DateConverter.toDate(LocalDate.of(2026, 3, 1));
        Date end = DateConverter.toDate(LocalDate.of(2026, 3, 19));
        assertEquals(18, DateOperations.daysBetween(start, end));
    }
}
