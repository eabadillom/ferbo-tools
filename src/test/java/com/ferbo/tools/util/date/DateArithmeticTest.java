package com.ferbo.tools.util.date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;

public class DateArithmeticTest {

    @Test
    public void addDaysLocalDate() {
        LocalDate date = LocalDate.of(2024, 3, 19);
        LocalDate result = DateArithmetic.addDays(date, 5);
        assertEquals(LocalDate.of(2024, 3, 24), result);
    }

    @Test
    public void addMonthsLocalDate() {
        LocalDate date = LocalDate.of(2024, 1, 31);
        LocalDate result = DateArithmetic.addMonths(date, 1);
        assertEquals(LocalDate.of(2024, 2, 29), result); // leap year
    }

    @Test
    public void addYearsLocalDate() {
        LocalDate date = LocalDate.of(2020, 2, 29);
        LocalDate result = DateArithmetic.addYears(date, 1);
        assertEquals(LocalDate.of(2021, 2, 28), result);
    }

    @Test
    public void addDaysLegacyDate() {
        Date date = new Date();
        Date result = DateArithmetic.addDays(date, 7);
        assertNotNull(result);
    }
}
