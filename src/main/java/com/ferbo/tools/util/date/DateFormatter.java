package com.ferbo.tools.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class DateFormatter {

    private DateFormatter() {
        // Utility class
    }

    // ---------- Parse Strings ----------
    public static LocalDate parseToLocalDate(String dateStr, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, locale);
        return LocalDate.parse(dateStr, formatter);
    }

    public static LocalDateTime parseToLocalDateTime(String dateStr, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, locale);
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static Date parseToDate(String dateStr, String pattern, TimeZone timeZone) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(timeZone);
        return sdf.parse(dateStr);
    }

    // ---------- Format Dates ----------
    public static String format(LocalDate date, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, locale);
        return date.format(formatter);
    }

    public static String format(LocalDateTime dateTime, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, locale);
        return dateTime.format(formatter);
    }

    public static String format(Date date, String pattern, TimeZone timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }

    // ---------- Overloads con defaults ----------
    public static LocalDate parseToLocalDate(String dateStr, String pattern) {
        return parseToLocalDate(dateStr, pattern, Locale.getDefault());
    }

    public static LocalDateTime parseToLocalDateTime(String dateStr, String pattern) {
        return parseToLocalDateTime(dateStr, pattern, Locale.getDefault());
    }

    public static Date parseToDate(String dateStr, String pattern) throws ParseException {
        return parseToDate(dateStr, pattern, TimeZone.getDefault());
    }

    public static String format(LocalDate date, String pattern) {
        return format(date, pattern, Locale.getDefault());
    }

    public static String format(LocalDateTime dateTime, String pattern) {
        return format(dateTime, pattern, Locale.getDefault());
    }

    public static String format(Date date, String pattern) {
        return format(date, pattern, TimeZone.getDefault());
    }
}
