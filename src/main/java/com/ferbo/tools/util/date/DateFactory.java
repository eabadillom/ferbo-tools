package com.ferbo.tools.util.date;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class DateFactory {

    private DateFactory() {
        // Utility class
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDate today() {
        return LocalDate.now();
    }

    public static LocalDate of(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }
}
