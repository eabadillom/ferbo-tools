package com.ferbo.tools.value.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import com.ferbo.tools.exception.ValidationException;

/**
 * Value Object que representa un impuesto.
 *
 * Características:
 * - Inmutable
 * - Seguro
 * - Basado en porcentaje (rate)
 *
 * Ejemplo:
 * 0.16 = 16% (IVA)
 */
public final class Tax {

    private static final int SCALE_INTERNAL = 6; // Precisión interna suficiente

    private final BigDecimal rate;

    /**
     * Constructor privado, usar factories.
     */
    private Tax(BigDecimal rate) {
        validate(rate);
        this.rate = normalize(rate);
    }

    /**
     * Factory con valor decimal (0.16 = 16%)
     */
    public static Tax of(BigDecimal rate) {
        return new Tax(rate);
    }

    /**
     * Factory con porcentaje entero (16 = 16%)
     */
    public static Tax ofPercentage(BigDecimal percentage) {
        if (percentage == null) {
            throw new ValidationException("El porcentaje no puede ser nulo");
        }
        BigDecimal decimalRate = percentage.divide(BigDecimal.valueOf(100), SCALE_INTERNAL, RoundingMode.HALF_UP);
        return new Tax(decimalRate);
    }

    /**
     * Normaliza la tasa con escala interna y elimina ceros finales.
     */
    private BigDecimal normalize(BigDecimal rate) {
        return rate.setScale(SCALE_INTERNAL, RoundingMode.HALF_UP)
                   .stripTrailingZeros();
    }

    /**
     * Validaciones básicas de la tasa.
     */
    private void validate(BigDecimal rate) {
        if (rate == null) {
            throw new ValidationException("La tasa del impuesto no puede ser nula");
        }

        if (rate.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("La tasa no puede ser negativa");
        }

        if (rate.compareTo(BigDecimal.ONE) > 0) {
            throw new ValidationException("La tasa no puede ser mayor a 1");
        }
    }

    /**
     * Calcula el impuesto sobre la base.
     */
    public Money calculate(Money base) {
        if (base == null) {
            throw new ValidationException("El monto base no puede ser nulo");
        }
        return base.multiply(rate);
    }

    /**
     * Aplica el impuesto a la base.
     */
    public Money apply(Money base) {
        return base.add(calculate(base));
    }

    /**
     * Devuelve la tasa decimal.
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Devuelve el porcentaje equivalente (ej: 16.00).
     */
    public BigDecimal asPercentage() {
        return rate.multiply(BigDecimal.valueOf(100))
                   .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tax)) return false;

        Tax tax = (Tax) o;
        return rate.compareTo(tax.rate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate.stripTrailingZeros());
    }

    /**
     * Representación técnica (determinista)
     */
    @Override
    public String toString() {
        return "TAX " + rate.toPlainString();
    }
}