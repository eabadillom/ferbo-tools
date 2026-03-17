package com.ferbo.tools.value.money;

import java.math.BigDecimal;
import java.util.Objects;

import com.ferbo.tools.exception.ValidationException;

/**
 * Value Object que representa un impuesto.
 * 
 * Carracteristicas:
 * - Inmutable
 * - Seguro
 * - Basado en porcentaje (rate)
 * 
 * Ejemplo:
 * 0.16 = 16% (IVA)
 */
public final class Tax {

    private final BigDecimal rate;

    /**
     * Constructor principal.
     * 
     * @param rate porcentaje del impuesto (ej: 0.16 = 16%)
     */
    public Tax(BigDecimal rate) {
        validate(rate);
        this.rate = rate;
    }

    /**
     * Validación interna del impuesto.
     */
    private void validate(BigDecimal rate) {
        if (rate == null) {
            throw new ValidationException("La tasa del impuesto no pude ser nula");
        }

        if (rate.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("La tasa del impuesto no puede ser negativa");
        }

        if (rate.compareTo(BigDecimal.ONE) > 0) {
            throw new ValidationException("La tasa del impuesto no puede ser mayor a 1");
        }
    }

    /**
     * Calcula el monto del impuesto sobre una base.
     * 
     * @param base monto base
     * @return monto del impuesto
     */
    public Money calculate(Money base) {
        if (base == null) {
            throw new ValidationException("El monto base no puede ser nulo");
        }

        return base.multiply(rate);
    }

    /**
     * Aplica el impuesto al monto base. 
     * 
     * @param base monto base
     * @return monto total (base + impuesto)
     */
    public Money apply(Money base) {
        Money taxAmount = calculate(base);
        return base.add(taxAmount);
    }

    /**
     * Obtiene la tasa del impuesto.
     */
    public BigDecimal getRate(){
        return rate;
    }

    /**
     * equals basado en la tasa.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tax)) return false;

        Tax tax = (Tax) o;
        return Objects.equals(rate, tax.rate);
    }

    /**
     * hashCode consistente con equals.
     */
    @Override 
    public int hashCode() {
        return Objects.hash(rate);
    }

    /**
     * Representación en texto.
     */
    @Override
    public String toString(){
        return "Tax{" +
               "rate=" + rate +
               '}';
    }
}
