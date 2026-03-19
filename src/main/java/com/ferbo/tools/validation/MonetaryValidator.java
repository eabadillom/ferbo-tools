package com.ferbo.tools.validation;

import java.util.ArrayList;
import java.util.List;

import com.ferbo.tools.result.Message;
import com.ferbo.tools.result.MessageLevel;
import com.ferbo.tools.result.OperationResult;
import com.ferbo.tools.result.ResultBuilder;
import com.ferbo.tools.value.money.Money;

/**
 * Validador de objetos Money.
 *
 * <p>
 * Permite validar reglas básicas sobre dinero:
 * - objeto no nulo
 * - cantidad positiva
 * - moneda obligatoria (opcional)
 * </p>
 */
public class MonetaryValidator implements Validator<Money> {

    private final boolean positiveOnly;
    private final String requiredCurrency;

    /**
     * Constructor con opciones de validación.
     *
     * @param positiveOnly     si true, el valor debe ser mayor que cero
     * @param requiredCurrency moneda requerida (nullable)
     */
    public MonetaryValidator(boolean positiveOnly, String requiredCurrency) {
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
    public OperationResult<Money> validate(Money target) {

        List<Message> messages = new ArrayList<>();

        if (target == null) {
            messages.add(new Message(MessageLevel.ERROR, "Dinero inválido", "El objeto Money no puede ser nulo"));
        } else {
            if (positiveOnly && target.getAmount().doubleValue() <= 0) {
                messages.add(new Message(MessageLevel.ERROR, "Dinero inválido", "El valor debe ser positivo"));
            }
            if (requiredCurrency != null
                    && !requiredCurrency.equals(target.getCurrency().getCurrencyCode())) { 
                messages.add(
                        new Message(MessageLevel.ERROR, "Dinero inválido", "La moneda debe ser " + requiredCurrency));
            }
        }

        // Construir el OperationResult final
        ResultBuilder<Money> builder;
        if (!messages.isEmpty()) {
            builder = ResultBuilder.<Money>failure().data(target);
        } else {
            builder = ResultBuilder.<Money>success().data(target);
        }

        for (Message m : messages) {
            builder.message(m);
        }

        return builder.build();
    }
}
