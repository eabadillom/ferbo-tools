package com.ferbo.tools.value.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import com.ferbo.tools.exception.BusinessException;
import com.ferbo.tools.exception.ValidationException;

/**
 * Implementación estándar de redondeo financiero.
 *
 * Reglas:
 * - Usa RoundingMode.HALF_UP (común en sistemas financieros)
 * - Respeta la escala de la moneda
 * - Agrega precisión extra en divisiones para evitar pérdida de datos
 */
public class StandardRoundingStrategy implements RoundingStrategy {

    private static final RoundingMode DEFAULT_MODE = RoundingMode.HALF_UP;
    private static final int EXTRA_SCALE = 4;

    @Override
    public BigDecimal round(BigDecimal amount, Currency currency) {
        validate(amount, currency);

        int scale = resolveScale(currency);
        return amount.setScale(scale, DEFAULT_MODE);
    }

    @Override
    public BigDecimal divide(BigDecimal amount, BigDecimal divisor, Currency currency) {
        validate(amount, currency);

        if (divisor == null) {
            throw new ValidationException("El divisor no puede ser nulo");
        }

        if (BigDecimal.ZERO.compareTo(divisor) == 0) {
            throw new BusinessException("No se puede dividir entre cero");
        }

        int scale = resolveScale(currency);
        int extendedScale = scale + EXTRA_SCALE;

        // División con precisión extendida
        BigDecimal result = amount.divide(divisor, extendedScale, DEFAULT_MODE);

        // Redondeo final a escala de moneda
        return result.setScale(scale, DEFAULT_MODE);
    }

    /**
     * Validaciones comunes.
     */
    private void validate(BigDecimal amount, Currency currency) {
        if (amount == null) {
            throw new ValidationException("El monto no puede ser nulo");
        }

        if (currency == null) {
            throw new ValidationException("La moneda no puede ser nula");
        }
    }

    /**
     * Obtiene la escala segura de la moneda.
     */
    private int resolveScale(Currency currency) {
        int digits = currency.getDefaultFractionDigits();
        return (digits < 0) ? 2 : digits;
    }
}