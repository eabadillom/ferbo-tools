package com.ferbo.tools.value.money;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import com.ferbo.tools.exception.ValidationException;

/**
 * Formateo de montos monetarios.
 *
 * Características:
 * - Formato para UI (locales)
 * - Formato técnico (logs, documentos)
 * - Basado en CurrencyUtils para precisión
 */
public final class MoneyFormatter {

    private MoneyFormatter() {
        // No instanciable
    }

    /**
     * Formato para UI según Locale.
     * Ej: $1,000.00
     */
    public static String format(Money money, Locale locale) {
        if (money == null) {
            throw new ValidationException("Money no puede ser nulo");
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }

        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        Currency currency = money.getCurrency();

        formatter.setCurrency(currency);
        int scale = CurrencyUtils.getScale(currency);
        formatter.setMinimumFractionDigits(scale);
        formatter.setMaximumFractionDigits(scale);

        BigDecimal roundedAmount = CurrencyUtils.round(money.getAmount(), currency);
        return formatter.format(roundedAmount);
    }

    /**
     * Formato con código de moneda (útil para documentos).
     * Ej: $1,000.00 MXN
     */
    public static String formatWithCode(Money money, Locale locale) {
        return format(money, locale) + " " + money.getCurrency().getCurrencyCode();
    }

    /**
     * Formato técnico determinista.
     * Ej: MXN 1000.00
     */
    public static String formatTechnical(Money money) {
        if (money == null) {
            throw new ValidationException("Money no puede ser nulo");
        }

        BigDecimal rounded = CurrencyUtils.round(money.getAmount(), money.getCurrency());
        return money.getCurrency().getCurrencyCode() + " " + rounded.toPlainString();
    }
}
