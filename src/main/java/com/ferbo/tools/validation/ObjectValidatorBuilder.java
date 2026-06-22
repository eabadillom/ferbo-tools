package com.ferbo.tools.validation;

import java.util.Currency;
import java.util.function.Consumer;
import java.util.function.Function;

import com.ferbo.tools.value.money.Money;

public class ObjectValidatorBuilder<T> {

    private final T target;
    private final String objectName;
    private final Notification notification = new Notification();

    public ObjectValidatorBuilder(String objectName, T target) {
        this.objectName = objectName;
        this.target = target;
    }

    // --------------------------
    // VALIDACIÓN DEL OBJETO
    // --------------------------

    /**
     * Valida que el objeto principal no sea nulo.
     */
    public ObjectValidatorBuilder<T> validateObject() {
        ObjectValidator.notNull(target, objectName, notification);
        return this;
    }

    // --------------------------
    // VALIDACIONES DE CAMPOS
    // --------------------------

    private boolean canValidateFields() {
        return target != null;
    }

    public ObjectValidatorBuilder<T> texto(String field, Function<T, String> extractor) {
        if (!canValidateFields()) return this;

        String value = extractor.apply(target);
        Notification local = new Notification();
        new TextValidator().validate(value, local);

        agregarErrores(field, local);
        return this;
    }

    public ObjectValidatorBuilder<T> integer(String field, Function<T, Integer> extractor, int min, int max) {
        if (!canValidateFields()) return this;

        Integer value = extractor.apply(target);
        Notification local = new Notification();
        new IntegerValidator(min, max).validate(value, local);

        agregarErrores(field, local);
        return this;
    }

    public ObjectValidatorBuilder<T> monetary(String field, Function<T, Money> extractor, boolean positiveOnly, Currency currency) {
        if (!canValidateFields()) return this;

        Money value = extractor.apply(target);
        Notification local = new Notification();
        new MonetaryValidator(positiveOnly, currency).validate(value, local);

        agregarErrores(field, local);
        return this;
    }

    // --------------------------
    // VALIDACIONES ANIDADAS
    // --------------------------

    public <R> ObjectValidatorBuilder<T> validateNested(
            String field,
            Function<T, R> extractor,
            Consumer<ObjectValidatorBuilder<R>> nestedValidatorConsumer) {

        if (!canValidateFields()) return this;

        R nestedObject = extractor.apply(target);
        ObjectValidatorBuilder<R> nestedValidator = new ObjectValidatorBuilder<>(objectName + "." + field, nestedObject);

        nestedValidatorConsumer.accept(nestedValidator);

        // Propagar errores del nested
        for (String error : nestedValidator.getNotification().getErrors()) {
            notification.addError(error);
        }

        return this;
    }

    // --------------------------
    // FINALIZACIÓN
    // --------------------------

    public void validateOrThrow() {
        notification.throwIfHasErrors();
    }

    public Notification getNotification() {
        return notification;
    }

    // --------------------------
    // HELPERS
    // --------------------------

    private void agregarErrores(String field, Notification local) {
        for (String error : local.getErrors()) {
            notification.addError(objectName + "." + field + ": " + error);
        }
    }
}