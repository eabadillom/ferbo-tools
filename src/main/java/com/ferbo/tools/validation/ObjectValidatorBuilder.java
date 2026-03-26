package com.ferbo.tools.validation;

import java.util.Currency;

import com.ferbo.tools.value.money.Money;

public class ObjectValidatorBuilder {

    private final Object target;
    private final Notification notification;

    public ObjectValidatorBuilder(Object target) {
        this.target = target;
        this.notification = new Notification();
    }

    // --------------------------
    // VALIDACIONES
    // --------------------------

    public ObjectValidatorBuilder texto(String field, String value) {
        Notification local = new Notification();
        new TextValidator().validate(value, local);
        agregarErrores(field, local);
        return this;
    }

    public ObjectValidatorBuilder integer(String field, Integer value, Integer min, Integer max) {
        Notification local = new Notification();
        new IntegerValidator(min, max).validate(value, local);
        agregarErrores(field, local);
        return this;
    }

    public ObjectValidatorBuilder monetary(String field, Money value, boolean positiveOnly, Currency currency) {
        Notification local = new Notification();
        new MonetaryValidator(positiveOnly, currency).validate(value, local);
        agregarErrores(field, local);
        return this;
    }

    public ObjectValidatorBuilder notNull(String field, Object value) {
        if (value == null) {
            notification.addError(field + " no debe ser nulo");
        }
        return this;
    }

    // --------------------------
    // FINALIZACIÓN
    // --------------------------

    public void build() {
        notification.throwIfHasErrors();
    }

    // --------------------------
    // HELPERS
    // --------------------------

    private void agregarErrores(String field, Notification local) {
        for (String error : local.getErrors()) {
            notification.addError(field + ": " + error);
        }
    }
}