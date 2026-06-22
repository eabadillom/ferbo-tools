package com.ferbo.tools.value.money;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

import com.ferbo.tools.exception.BusinessException;
import com.ferbo.tools.exception.ValidationException;

/**
 * Value Object que representa una cantidad monetaria segura e inmutable.
 *
 * Características:
 * - Inmutable
 * - Usa BigDecimal (precisión exacta)
 * - Seguro en operaciones
 * - Dependiente de la moneda
 * - Usa estrategia de redondeo consistente
 *
 * Permite valores:
 * - Positivos
 * - Negativos
 * - Cero
 */
public final class Money implements Comparable<Money> {

    private final BigDecimal amount;
    private final Currency currency;
    private final RoundingStrategy roundingStrategy;

    /**
     * Constructor por defecto: usa rounding estándar.
     */
    public Money(BigDecimal amount, Currency currency) {
        this(amount, currency, new StandardRoundingStrategy());
    }

    /**
     * Constructor principal con estrategia de redondeo.
     */
    public Money(BigDecimal amount, Currency currency, RoundingStrategy roundingStrategy) {
        validate(amount, currency);

        if (roundingStrategy == null) {
            throw new ValidationException("RoundingStrategy no puede ser nula");
        }

        this.currency = currency;
        this.roundingStrategy = roundingStrategy;
        this.amount = roundingStrategy.round(amount, currency);
    }

    /**
     * Devuelve un objeto money con el valor inicial de zero
     * @param currency
     * @return Money
     */
    public static Money zero(Currency currency) {
        return new Money(BigDecimal.ZERO, currency);
    }

    /**
     * Factory method principal.
     */
    public static Money of(BigDecimal amount, Currency currency) {
        return new Money(amount, currency);
    }

    /**
     * Factory conveniente con String (evita problemas de double).
     */
    public static Money of(String amount, String currencyCode) {
        return new Money(
                new BigDecimal(amount),
                Currency.getInstance(currencyCode));
    }

    /**
     * Crea nueva instancia preservando currency y roundingStrategy.
     */
    private Money newInstance(BigDecimal newAmount) {
        return new Money(newAmount, this.currency, this.roundingStrategy);
    }

    /**
     * Validaciones básicas.
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
     * Suma segura.
     */
    public Money add(Money other) {
        validateSameCurrency(other);
        return newInstance(this.amount.add(other.amount));
    }

    /**
     * Resta segura.
     */
    public Money subtract(Money other) {
        validateSameCurrency(other);
        return newInstance(this.amount.subtract(other.amount));
    }

    /**
     * Multiplicación segura.
     */
    public Money multiply(BigDecimal factor) {
        if (factor == null) {
            throw new ValidationException("El factor no puede ser nulo");
        }

        return newInstance(this.amount.multiply(factor));
    }

    /**
     * División segura delegada a la estrategia de redondeo.
     */
    public Money divide(BigDecimal divisor) {
        BigDecimal result = roundingStrategy.divide(this.amount, divisor, this.currency);
        return newInstance(result);
    }

    /**
     * Validación de moneda.
     */
    private void validateSameCurrency(Money other) {
        if (other == null) {
            throw new ValidationException("El valor a operar no puede ser nulo");
        }

        if (!this.currency.equals(other.currency)) {
            throw new BusinessException("Las monedas deben ser iguales para operar");
        }
    }

    @Override
    public int compareTo(Money other) {
        Objects.requireNonNull(other, "Money a comparar no puede ser null");
        validateSameCurrency(other);
        return this.amount.compareTo(other.amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public boolean isZero() {
        return amount.signum() == 0;
    }

    public boolean isNegative() {
        return amount.signum() < 0;
    }

    public boolean isPositive() {
        return amount.signum() > 0;
    }

    public Money negate() {
        return newInstance(this.amount.negate());
    }

    /**
     * Valor absoluto.
     */
    public Money abs() {
        return isNegative() ? negate() : this;
    }

    /**
     * Verifica si comparten moneda.
     */
    public boolean sameCurrency(Money other) {
        return other != null && this.currency.equals(other.currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Money))
            return false;

        Money money = (Money) o;

        return amount.compareTo(money.amount) == 0 &&
                currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount.stripTrailingZeros(), currency);
    }

    /**
     * Representación técnica (NO UI).
     */
    @Override
    public String toString() {
        return MoneyFormatter.formatTechnical(this);
    }
}