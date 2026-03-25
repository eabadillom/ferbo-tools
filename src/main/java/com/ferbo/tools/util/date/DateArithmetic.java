package com.ferbo.tools.util.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public final class DateArithmetic {

    private DateArithmetic() {
        // Utility class
    }

    // ---------- LocalDate operations ----------
    public static LocalDate addDays(LocalDate date, int days) {
        return date.plusDays(days);
    }

    public static LocalDate addMonths(LocalDate date, int months) {
        return date.plusMonths(months);
    }

    public static LocalDate addYears(LocalDate date, int years) {
        return date.plusYears(years);
    }

    // ---------- LocalDateTime operations ----------
    public static LocalDateTime addDays(LocalDateTime dateTime, int days) {
        return dateTime.plusDays(days);
    }

    public static LocalDateTime addMonths(LocalDateTime dateTime, int months) {
        return dateTime.plusMonths(months);
    }

    public static LocalDateTime addYears(LocalDateTime dateTime, int years) {
        return dateTime.plusYears(years);
    }

    // ---------- Legacy Date support ----------
    public static Date addDays(Date date, int days) {
        LocalDateTime ldt = DateConverter.toLocalDateTime(date);
        return DateConverter.toDate(ldt.plusDays(days));
    }

    public static Date addMonths(Date date, int months) {
        LocalDateTime ldt = DateConverter.toLocalDateTime(date);
        return DateConverter.toDate(ldt.plusMonths(months));
    }

    public static Date addYears(Date date, int years) {
        LocalDateTime ldt = DateConverter.toLocalDateTime(date);
        return DateConverter.toDate(ldt.plusYears(years));
    }
}