package com.ferbo.tools.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Test;

import com.ferbo.tools.value.money.Money;

public class MonetaryValidatorTest {

    private Notification notification;

    private final Currency eur = Currency.getInstance("EUR");
    private final Currency usd = Currency.getInstance("USD");

    @Test
    public void shouldReturnSuccessForValidMoney() {
        Money money = new Money(new BigDecimal("100.00"), usd);
        MonetaryValidator validator = new MonetaryValidator(true, usd);
        notification = new Notification();

        validator.validate(money, notification);

        assertFalse(notification.hasErrors());
    }

    @Test
    public void shouldReturnFailureForNullMoney() {
        MonetaryValidator validator = new MonetaryValidator(true, usd);
        notification = new Notification();

        validator.validate(null, notification);

        assertTrue(notification.hasErrors());
        assertEquals("El objeto Money no puede ser nulo", notification.getErrors().get(0));
    }

    @Test
    public void shouldReturnFailureForNegativeMoney() {
        Money money = new Money(new BigDecimal("-50.00"), usd);
        MonetaryValidator validator = new MonetaryValidator(true, usd);
        notification = new Notification();

        validator.validate(money, notification);

        assertTrue(notification.hasErrors());
        assertEquals("El valor debe ser positivo", notification.getErrors().get(0));
    }

    @Test
    public void shouldReturnFailureForWrongCurrency() {
        Money money = new Money(new BigDecimal("100.00"), eur);
        MonetaryValidator validator = new MonetaryValidator(true, usd);
        notification = new Notification();

        validator.validate(money, notification);

        assertTrue(notification.hasErrors());
        assertEquals("La moneda debe ser USD", "La moneda debe ser " + usd);
    }

    @Test
    public void shouldReturnSuccessWithoutCurrencyCheck() {
        Money money = new Money(new BigDecimal("50.00"), eur);
        MonetaryValidator validator = new MonetaryValidator(true, null);
        notification = new Notification();

        validator.validate(money, notification);

        assertFalse(notification.hasErrors());
    }

    @Test
    public void shouldReturnSuccessForZeroOrNegativeWhenNotPositiveOnly() {
        Money money = new Money(new BigDecimal("-10.00"), usd);
        MonetaryValidator validator = new MonetaryValidator(false, usd);
        notification = new Notification();

        validator.validate(money, notification);

        assertFalse(notification.hasErrors());
    }
}