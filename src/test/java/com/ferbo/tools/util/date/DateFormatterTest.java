package com.ferbo.tools.util.date;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

public class DateFormatterTest {
    
    @Test
    public void testParseLocalDate() {
        String dateStr = "19/03/2026";
        LocalDate date = DateFormatter.parseToLocalDate(dateStr, "dd/MM/yyyy");
        assertEquals(19, date.getDayOfMonth());
        assertEquals(3, date.getMonthValue());
        assertEquals(2026, date.getYear());
    }

    @Test
    public void testFormatLocalDate() {
        LocalDate date = LocalDate.of(2026, 3, 19);
        String formatted = DateFormatter.format(date, "dd-MM-yyyy");
        assertEquals("19-03-2026", formatted);
    }

    @Test
    public void testParseLocalDateTime() {
        String dtStr = "19/03/2026 15:30";
        LocalDateTime ldt = DateFormatter.parseToLocalDateTime(dtStr, "dd/MM/yyyy HH:mm");
        assertEquals(19, ldt.getDayOfMonth());
        assertEquals(15, ldt.getHour());
    }

    @Test
    public void testFormatDate() throws ParseException {
        String dateStr = "2026-03-19";
        Date date = DateFormatter.parseToDate(dateStr, "yyyy-MM-dd");
        String formatted = DateFormatter.format(date, "dd/MM/yyyy");
        assertEquals("19/03/2026", formatted);
    }
}
