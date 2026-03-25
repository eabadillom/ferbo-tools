package com.ferbo.tools.util.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public final class DateOperations {

    private DateOperations() {
        // Utility class
    }

    // ---------- Comparaciones básicas ----------
    public static boolean isBefore(LocalDate date1, LocalDate date2) {
        return date1.isBefore(date2);
    }

    public static boolean isAfter(LocalDate date1, LocalDate date2) {
        return date1.isAfter(date2);
    }

    public static boolean isBetween(LocalDate target, LocalDate start, LocalDate end) {
        return ( !target.isBefore(start) && !target.isAfter(end) );
    }

    // ---------- LocalDateTime ----------
    public static boolean isBefore(LocalDateTime dt1, LocalDateTime dt2) {
        return dt1.isBefore(dt2);
    }

    public static boolean isAfter(LocalDateTime dt1, LocalDateTime dt2) {
        return dt1.isAfter(dt2);
    }

    public static boolean isBetween(LocalDateTime target, LocalDateTime start, LocalDateTime end) {
        return ( !target.isBefore(start) && !target.isAfter(end) );
    }

    // ---------- Diferencias ----------
    public static long daysBetween(LocalDate start, LocalDate end) {
        return ChronoUnit.DAYS.between(start, end);
    }

    public static long weeksBetween(LocalDate start, LocalDate end) {
        return ChronoUnit.WEEKS.between(start, end);
    }

    public static long daysBetween(Date start, Date end) {
        LocalDate ldStart = DateConverter.toLocalDate(start);
        LocalDate ldEnd = DateConverter.toLocalDate(end);
        return daysBetween(ldStart, ldEnd);
    }

    public static long weeksBetween(Date start, Date end) {
        LocalDate ldStart = DateConverter.toLocalDate(start);
        LocalDate ldEnd = DateConverter.toLocalDate(end);
        return weeksBetween(ldStart, ldEnd);
    }
}
