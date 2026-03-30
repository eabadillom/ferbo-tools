package com.ferbo.tools.validation;

import java.util.Currency;

import com.ferbo.tools.value.money.Money;

/**
 * Validador de objetos Money.
 *
 * <p>
 * Permite validar reglas básicas sobre dinero:
 * - objeto no nulo
 * - cantidad positiva (opcional)
 * - moneda obligatoria (opcional)
 * </p>
 */
public class MonetaryValidator implements Validator<Money> {

    private final boolean positiveOnly;
    private final Currency requiredCurrency;

    /**
     * Constructor con opciones de validación.
     *
     * @param positiveOnly     si true, el valor debe ser mayor que cero
     * @param requiredCurrency moneda requerida (nullable)
     */
    public MonetaryValidator(boolean positiveOnly, Currency requiredCurrency) {
        this.positiveOnly = positiveOnly;
        this.requiredCurrency = requiredCurrency;
    }

    /**
     * Constructor por defecto: cualquier Money válido
     */
    public MonetaryValidator() {
        this(false, null);
    }

    @Override
    public void validate(Money target, Notification notification) {

        // Validación de nulo
        if (target == null) {
            notification.addError("El objeto Money no puede ser nulo");
            return;
        }

        // Validación de monto positivo
        if (positiveOnly && target.getAmount() != null 
                && target.getAmount().doubleValue() <= 0) {
            notification.addError("El valor debe ser positivo");
        }

        // Validación de moneda requerida
        if (requiredCurrency != null) {
            if (target.getCurrency() == null ||
                !requiredCurrency.getCurrencyCode()
                    .equals(target.getCurrency().getCurrencyCode())) {

                notification.addError("La moneda debe ser " + requiredCurrency);
            }
        }
    }
}
