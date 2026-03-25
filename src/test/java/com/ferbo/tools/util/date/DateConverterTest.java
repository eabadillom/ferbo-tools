package com.ferbo.tools.util.date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

public class DateConverterTest {

    @Test
    public void shouldConvertDateToLocalDate() {
        Date date = new Date();

        LocalDate result = DateConverter.toLocalDate(date);

        assertNotNull(result);
    }

    @Test
    public void shouldConvertDateToLocalDateTime() {
        Date date = new Date();

        LocalDateTime result = DateConverter.toLocalDateTime(date);

        assertNotNull(result);
    }

    @Test
    public void shouldConvertLocalDateToDate() {
        LocalDate localDate = LocalDate.of(2024, 3, 15);

        Date result = DateConverter.toDate(localDate);

        assertNotNull(result);
    }

    @Test
    public void shouldConvertLocalDateTimeToDate() {
        LocalDateTime localDateTime = LocalDateTime.now();

        Date result = DateConverter.toDate(localDateTime);

        assertNotNull(result);
    }

    @Test
    public void shouldThrowExceptionWhenDateIsNull() {
        assertThrows(NullPointerException.class, () -> {
            DateConverter.toLocalDate(null);
        });
    }

    @Test
    public void shouldThrowExceptionWhenLocalDateIsNull() {
        assertThrows(NullPointerException.class, () -> {
            DateConverter.toDate((LocalDate) null);
        });
    }
}