package com.ferbo.tools.value.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Bidi;
import java.util.Currency;
import java.util.Objects;

import com.ferbo.tools.exception.BussinesException;
import com.ferbo.tools.exception.ValidationException;

/**
 * Value Object que representa una cantidad monetaria segura.
 * 
 * Características:
 * - Inmutable 
 * - Precio (usa BigDecimal)
 * - Seguro en operaciones
 * - Dependiente de la moneda
 * 
 * Permite Valores:
 * - Positivos
 * - Negativos
 * - Cero
 */
public final class Money {

    private final BigDecimal amount;
    private final Currency currency;

    /**
     *  Constructor principal. 
     * 
     * @param amount Cantidad monetaria
     * @param currency Moneda
     */
    public Money (BigDecimal amount, Currency currency) {
        validate(amount, currency);

        int scale = currency.getDefaultFractionDigits();

        this.currency = currency;
        this.amount = amount.setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * Método de validación interna.
     */
    private void validate(BigDecimal amount, Currency currency){
        if (amount == null) {
            throw new ValidationException("El monto no puede ser nulo");
        }

        if (currency == null) {
            throw new ValidationException("La moneda no puede ser nula");
        }
    }

    /**
     * Suma dos valores monetarios.
     */
    public Money add(Money other) {
        validateSameCurrency(other);

        BigDecimal result = this.amount.add(other.amount);
        return new Money(result, this.currency);
    }

    /**
     * Resta dos valores monetarios.
     */
    public Money substract(Money other) {
        validateSameCurrency(other);

        BigDecimal result = this.amount.subtract(other.amount);
        return new Money(result, this.currency);
    }

    /**
     * Multiplica el monto por un factor.
     */
    public Money multiply(BigDecimal factor) {
        if (factor == null) {
            throw new  ValidationException("El factor no puede ser nulo.");
        }

        BigDecimal result = this.amount.multiply(factor);
        return new Money(result, this.currency);
    }

    /**
     * Divide el monto por un divisor.
     */
    public Money divide(BigDecimal divisor){
        if (divisor == null) {
            throw new ValidationException("EL divisor no puede ser nulo.");
        }

        if (BigDecimal.ZERO.compareTo(divisor) == 0) {
            throw new BussinesException("No se puede dividir entre cero");
        }

        int scale = currency.getDefaultFractionDigits();

        BigDecimal result = this.amount.divide(divisor, scale, RoundingMode.HALF_UP);
        return new Money(result, this.currency);
    }

    /**
     * Valida que ambas monedas sean iguales.
     */
    private void validateSameCurrency(Money other) {
        if (other == null) {
            throw new ValidationException("El valor a operar no puede ser nulo");
        }

        if(!this.currency.equals(other.currency)){
            throw new BussinesException("Las monedas deben ser iguales para operar");
        }
    }

    /**
     * Obtiene el monto.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Obtiene la moneda
    */
   public Currency getCurrency() {
    return currency;
   }

   /**
    * Indica si el valor es cero.
    */
   public boolean isZero(){
    return BigDecimal.ZERO.compareTo(this.amount) == 0;
   }

   /**
    * Indica si el valor es negativo.
    */
   public boolean isNegative() {
    return this.amount.signum() < 0;
   }

   /**
    * Indica si el valor es positivo.
    */
   public boolean isPositive() {
    return this.amount.signum() > 0;
   }

   /**
    * equals baso en monto y moneda.
    */
   @Override
   public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Money)) return false;

    Money money = (Money) o;

    return Objects.equals(amount, money.amount) && Objects.equals(currency, money.currency);
   }

   /**
    * hashCode consistente con equals.
    */
   @Override 
   public int hashCode() {
        return Objects.hash(amount, currency);
   }

   /**
    * Representación en texto.
    */
   @Override
   public String toString(){
        return currency.getCurrencyCode() + " " + amount;
   }
}