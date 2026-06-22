package com.ferbo.tools.value.money;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import com.ferbo.tools.exception.ValidationException;

/**
 * Pruebas unitarias para la clase MoneyFormatter.
 */
public class MoneyFormatterTest {

    @Test
    void testFormatUS() {
        Money money = Money.of("1000.5", "USD");
        String formatted = MoneyFormatter.format(money, Locale.US);
        assertEquals("$1,000.50", formatted);
    }

    @Test
    void testFormatWithCodeMXN() {
        Money money = Money.of("1000.5", "MXN");
        String formatted = MoneyFormatter.formatWithCode(money, new Locale("es","MX"));
        assertEquals("$1,000.50 MXN", formatted);
    }

    @Test
    void testFormatTechnical() {
        Money money = Money.of("1000.567", "JPY");
        String technical = MoneyFormatter.formatTechnical(money);
        assertEquals("JPY 1001", technical); // JPY sin decimales
    }

    @Test
    void testNullMoneyThrows() {
        assertThrows(ValidationException.class, () -> MoneyFormatter.format(null, Locale.US));
        assertThrows(ValidationException.class, () -> MoneyFormatter.formatTechnical(null));
    }
}
