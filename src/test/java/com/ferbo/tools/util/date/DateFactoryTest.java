package com.ferbo.tools.util.date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

import com.ferbo.tools.exception.SystemException;

public class DateFactoryTest {

    @Test
    public void shouldReturnCurrentDateTime() {
        LocalDateTime now = DateFactory.now();

        assertNotNull(now);
    }

    @Test
    public void shouldReturnTodayDate() {
        LocalDate today = DateFactory.today();

        assertNotNull(today);
    }

    @Test
    public void shouldCreateSpecificDate() {
        LocalDate date = DateFactory.of(2024, 3, 15);

        assertEquals(2024, date.getYear());
        assertEquals(3, date.getMonthValue());
        assertEquals(15, date.getDayOfMonth());
    }

    @Test
    public void shouldThrowExceptionForInvalidDate() {
        assertThrows(Exception.class, () -> {
            DateFactory.of(2024, 2, 30);
        });
    }
}