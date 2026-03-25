package com.ferbo.tools.util.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public final class DateConverter {

    private DateConverter() {
        // Utility class
    }

    public static LocalDate toLocalDate(Date date) {
        Objects.requireNonNull(date, "La fecha no debe ser nula");

        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        Objects.requireNonNull(date, "La fecha no debe ser nula");

        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Date toDate(LocalDate date) {
        Objects.requireNonNull(date, "La fecha no debe ser nula");

        return Date.from(
                date.atStartOfDay(ZoneId.systemDefault()).toInstant()
        );
    }

    public static Date toDate(LocalDateTime dateTime) {
        Objects.requireNonNull(dateTime, "La fecha no debe ser nula");

        return Date.from(
                dateTime.atZone(ZoneId.systemDefault()).toInstant()
        );
    }
}