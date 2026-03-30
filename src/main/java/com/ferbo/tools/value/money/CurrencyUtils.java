package com.ferbo.tools.value.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import com.ferbo.tools.exception.ValidationException;

/**
 * Utilidades puras relacionadas con Currency.
 *
 * Responsabilidades:
 * - Obtener escala de la moneda
 * - Redondear montos según la moneda
 * 
 * Nota: NO incluye formateo ni UI.
 */
public final class CurrencyUtils {

    private CurrencyUtils() {
        throw new UnsupportedOperationException("CurrencyUtils no se instancia");
    }

    /**
     * Devuelve la escala oficial de la moneda.
     * Ejemplo:
     * - USD -> 2
     * - JPY -> 0
     */
    public static int getScale(Currency currency) {
        if (currency == null) {
            throw new ValidationException("La moneda no puede ser nula");
        }
        int scale = currency.getDefaultFractionDigits();
        return Math.max(scale, 0);
    }

    /**
     * Redondea un monto a la escala de la moneda usando HALF_UP.
     */
    public static BigDecimal round(BigDecimal amount, Currency currency) {
        if (amount == null) {
            throw new ValidationException("El monto no puede ser nulo");
        }
        int scale = getScale(currency);
        return amount.setScale(scale, RoundingMode.HALF_UP);
    }
}
